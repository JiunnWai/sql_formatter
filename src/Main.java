public class Main {
    // Constants
    public final static String SELECT = "SELECT";
    public final static String FROM = "FROM";
    public final static String WHERE = "WHERE";
    public final static String AS = "AS";
    public final static String INDENT = "    ";

    // Global variables
    public static String[] keywords = {SELECT, FROM, WHERE, AS};

    public static void main(String[] args) {


        // Read string
        String input = "seLECt   o.order_date as orDer_date  ,\n       o.sys_order_id   \t \t as order_id,\n  o.resto_id\nFRom\n\n   dwh_resto.fact_orders_resto" +
                       " WheRE       \t custo_id = 106135 and o.status_id    =   4";

        // Perform string processing
        input = capitalization(input);
        input = removeSpaceChars(input);

        System.out.println("The input is:\n----------------------------------\n" + input + "\n");

        System.out.println("The select clause is:\n----------------------------------\n");
        String selectClause = extractString(input, SELECT, FROM);
        System.out.println(selectClause);
        System.out.println("AFTER FORMATTING SELECT:\n");
        selectClause = formatSelect(selectClause);
        System.out.println(selectClause);

//        System.out.println("The from clause is:\n----------------------------------\n");
//        String fromClause = extractString(input, FROM, WHERE);
//        System.out.println(fromClause);
//
//        System.out.println("The where clause is:\n----------------------------------\n");
//        String whereClause = extractString(input, WHERE, null);
//        System.out.println(whereClause);

    }

    /* Perform capitalization on the given string, uppercase all keywords, lower case all other words
       string : The string to process
       return : The processed string
     */
    public static String capitalization(String string) {
        String result = string.toLowerCase();
        result = result.replaceAll(SELECT.toLowerCase(), SELECT);
        result = result.replaceAll(FROM.toLowerCase(), FROM);
        result = result.replaceAll(WHERE.toLowerCase(), WHERE);
        result = result.replaceAll(AS.toLowerCase(), AS);
        return result;
    }

    /* Remove all special space, tab, newline characters.
       string: The string to process
       return: The processed string
     */
    public static String removeSpaceChars(String string) {
        string = string.replaceAll("\t", "");
        string = string.replaceAll("\n", "");
        return string;
    }

    /* Extract the given string and return a new string containing from start to end.
       string : The given string to create the substring from
       start  : Start keyword to include in the result (inclusive)
       end    : Ending keyword to stop at (not inclusive), if null, return until the end
       return : Returns the substring
    */
    public static String extractString(String string, String start, String end) {
        int startKeyIndex = string.indexOf(start);
        int endKeyIndex = end == null ? string.length() : string.indexOf(end);
        return string.substring(startKeyIndex, endKeyIndex);
    }

    /* Given the select clause string, format it by aligning indents and spaces.
       string: The select clause to format.
       return: The formatted select clause.
     */
    public static String formatSelect(String string) {
        String result = SELECT + "\n";
        string = string.substring(string.indexOf(SELECT) + SELECT.length());
        String[] columns = string.split(",");

        for (int i=0 ; i<columns.length ; i++) {
            columns[i] = columns[i].trim();
            columns[i] = INDENT + columns[i];
            columns[i] += i == columns.length - 1 ? "" : ",\n";
            result += columns[i];
        }

        result += formatAlias(columns);
        return result;
    }

    public static String formatAlias(String[] columns) {

    }

    /* Returns a boolean indicating if the string is a keyword.
       string: The string to check
       return: Boolean value
     */
    public static boolean isKeyword(String string) {
        boolean isKeyword = false;
        int i = 0;

        while (i < keywords.length && !isKeyword) {
            isKeyword = string.trim().toUpperCase().equals(keywords[i]);
            i++;
        }

        return isKeyword;
    }
}
