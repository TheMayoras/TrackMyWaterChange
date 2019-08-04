package themayoras.trackmywaterchange.dateformat;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MultiDateFormatBuilder {
    public static MultiDateFormatBuilder create() {
        return new MultiDateFormatBuilder();
    }

    private Map<Pattern, String> patterns;

    private String formatPattern;

    private MultiDateFormatBuilder() {
        patterns = new HashMap<>();
    }

    public MultiDateFormat build() {
        if (this.formatPattern == null) {
            this.formatPattern = patterns.entrySet().iterator().next().getValue();
        }
        
        return new MultiDateFormat(patterns, formatPattern);
    }

    public MultiDateFormatBuilder addFormat(String datePattern, String format) {
        Pattern pattern = Pattern.compile(datePattern);
        patterns.put(pattern, format);

        return this;
    }

    public MultiDateFormatBuilder addFormat(Pattern pattern, String format) {
        patterns.put(pattern, format);

        return this;
    }

    public MultiDateFormatBuilder setOutputFormat(String format) {
        this.formatPattern = format;

        return this;
    }
}
