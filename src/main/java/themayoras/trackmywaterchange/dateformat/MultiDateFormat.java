package themayoras.trackmywaterchange.dateformat;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MultiDateFormat extends DateFormat {

    private static final long serialVersionUID = -4835985088633860841L;

    private Map<Pattern, String> patterns;

    private String formatPattern;
    
    public MultiDateFormat(String formatPattern) {
        this.formatPattern = formatPattern;
    }

    public MultiDateFormat(Map<Pattern, String> patterns, String formatPattern) {
        this.patterns      = patterns;
        this.formatPattern = formatPattern;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return new SimpleDateFormat(formatPattern).format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        for (Map.Entry<Pattern, String> pattern : patterns.entrySet()) {
            if (pattern.getKey().matcher(source).matches()) {
                this.formatPattern = pattern.getValue();
                return new SimpleDateFormat(pattern.getValue()).parse(source, pos);
            }
        }
        
        return null;
    }
    
    public Map<Pattern, String> getPatterns() {
        return this.patterns;
    }

    public void setPatterns(Map<Pattern, String> patterns) {
        this.patterns = patterns;
    }

    public String getFormatPattern() {
        return this.formatPattern;
    }

    public void setFormatPattern(String formatPattern) {
        this.formatPattern = formatPattern;
    }

    public void addPattern(Pattern pattern, String format) {
        if (patterns == null) {
            patterns = new HashMap<>();
        }
        
        patterns.put(pattern, format);
    }
    
    public void addPattern(String pattern, String format) {
        if (patterns == null) {
            patterns = new HashMap<>();
        }
        
        patterns.put(Pattern.compile(pattern), format);
    }
    
}
