package net.syscon.s4.common.impl;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class SQLProvider {
	private static Logger log = LogManager.getLogger(SQLProvider.class.getName());

	private final Map<String, String> statements = new HashMap<>();

	private final ApplicationContext applicationContext;
	private final String schemaType;
	private String sqlPath;

	@Autowired
	public SQLProvider(ApplicationContext applicationContext, @Value("${schema.type}") String schemaType) {
		this.applicationContext = applicationContext;
		this.schemaType = schemaType;

	}

	public void loadSql(final String className, String path) {
		sqlPath = path.replace(".", "/").replaceAll("package net/syscon/s4/", "").trim() + "/";
		sqlPath = sqlPath.replaceAll("/impl/", "").trim() + "/";
		loadSqlResource("classpath:configs/sqls/" + sqlPath + className + ".sql");
		final String[] schemas = StringUtils.split(schemaType, ",");
		if (schemas != null) {
			Arrays.asList(schemas)
					.forEach(schema -> loadSqlResource("classpath:configs/sqls/" + sqlPath + className + ".sql"));
		}
	}

	private void loadSqlResource(String resourcePath) {
		final Resource resource = applicationContext.getResource(resourcePath);
		if (resource.exists()) {
			try {
				log.debug("Loading resource {}", resourcePath);
				loadFromStream(resource.getInputStream());
			} catch (final IOException ex) {
				log.error(ex.getMessage(), ex);
			}
		}
	}

	private void loadFromStream(final InputStream input) {
		final CharArrayWriter out = new CharArrayWriter();
		final char[] cbuf = new char[1024];
		try {
			final BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			int size = reader.read(cbuf);
			while (size > -1) {
				out.write(cbuf, 0, size);
				size = reader.read(cbuf);
			}
			parse(out.toCharArray());
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private int getNext(final char[] content, final int offset, final char searchFor) {
		if (offset >= 0) {
			for (int i = offset; i < content.length; i++) {
				if (content[i] == searchFor)
					return i;
			}
		}
		return -1;
	}

	private String makeString(final char[] content, int startIndex, int endIndex) {
		StringBuilder sb = new StringBuilder();
		for (int i = startIndex; i < content.length && i < endIndex; i++) {
			sb.append(content[i]);
		}
		return sb.toString();
	}

	private void parse(final char[] content) throws ParseException {
		final Map<String, String> newStatements = new HashMap<>();
		int i = 0;
		while (i < content.length) {
			final int startIndex = getNext(content, i, '{');
			final int endIndex = getNext(content, startIndex, '}');
			if (startIndex > -1 && endIndex > -1) {
				final String key = removeSpecialChars(makeString(content, i, startIndex).trim(), ' ', '\t', '\n', '\r');
				final String value = removeSpecialChars(makeString(content, startIndex + 1, endIndex), '\r', '\n');
				newStatements.put(key, value);
				i = endIndex + 1;
			} else {
				if (startIndex < 0 && endIndex < 0) {
					i = content.length;
				} else {
					throw new ParseException("Missing end brace + ", startIndex);
				}
			}
		}
		statements.putAll(newStatements);
	}

	private boolean in(char value, char[] elements) {
		boolean found = false;
		for (int i = 0; !found && i < elements.length; i++) {
			found = elements[i] == value;
		}
		return found;
	}

	private String removeCharsStartingWith(String text, char... elementsToRemove) {
		for (int i = 0; i < text.length(); i++) {
			if (!in(text.charAt(i), elementsToRemove)) {
				return text.substring(i);
			}
		}
		return "";
	}

	private String removeCharsEndingWith(String text, char... elementsToRemove) {
		for (int i = text.length() - 1; i > 0; i--) {
			if (!in(text.charAt(i), elementsToRemove)) {
				return text.substring(0, i + 1);
			}
		}
		return "";
	}

	private String removeSpecialChars(final String text, char... elementsToRemove) {
		String result = removeCharsStartingWith(text, elementsToRemove);
		return removeCharsEndingWith(result, elementsToRemove);
	}

	public String get(String name) {
		return statements.get(name);
	}
}
