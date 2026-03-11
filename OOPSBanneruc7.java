public class OOPSBanneruc7 {

    // Encapsulates a single character and its banner pattern
    static class CharacterPattern {
        private final char ch;
        private final String[] patternLines;

        public CharacterPattern(char ch, String[] patternLines) {
            this.ch = ch;
            this.patternLines = patternLines;
        }

        public char getCh() {
            return ch;
        }

        public String[] getPatternLines() {
            return patternLines;
        }

        public int getHeight() {
            return patternLines.length;
        }
    }

    // Static inner class: centralized map of character → pattern
    static class CharacterPatternMap {
        private static java.util.Map<Character, CharacterPattern> patternMap = new java.util.HashMap<>();

        static {
            initPatterns();
        }

        private static void initPatterns() {
            // 9x9 patterns for O, P, S, and space

            patternMap.put('O', new CharacterPattern('O', new String[]{
                    "    ***    ",
                    " **    **  ",
                    "**      ** ",
                    "**      ** ",
                    "**      ** ",
                    "**      ** ",
                    "**      ** ",
                    " **    **  ",
                    "    ***    "
            }));

            patternMap.put('P', new CharacterPattern('P', new String[]{
                    " ******    ",
                    " **    **  ",
                    " **      **",
                    " **    **  ",
                    " ******    ",
                    " **        ",
                    " **        ",
                    " **        ",
                    " **        "
            }));

            patternMap.put('S', new CharacterPattern('S', new String[]{
                    "    ***** ",
                    "  **      ",
                    " **       ",
                    "  **       ",
                    "    ***  ",
                    "       ** ",
                    "        ** ",
                    "       ** ",
                    "  *****  "
            }));

            // 9-row space to keep heights consistent
            patternMap.put(' ', new CharacterPattern(' ', new String[]{
                    "   ",
                    "   ",
                    "   ",
                    "   ",
                    "   ",
                    "   ",
                    "   ",
                    "   ",
                    "   "
            }));
        }

        public static CharacterPattern getPattern(char c) {
            CharacterPattern cp = patternMap.get(Character.toUpperCase(c));
            if (cp == null) {
                // Fallback: blank pattern of same height as O
                CharacterPattern ref = patternMap.get('O');
                String[] blank = new String[ref.getHeight()];
                java.util.Arrays.fill(blank, "         "); // 9 spaces
                cp = new CharacterPattern(c, blank);
            }
            return cp;
        }
    }

    // Prints a horizontal banner for a given word
    public static void printBanner(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }

        int height = CharacterPatternMap.getPattern(text.charAt(0)).getHeight();

        for (int row = 0; row < height; row++) {
            StringBuilder lineBuilder = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                CharacterPattern cp = CharacterPatternMap.getPattern(text.charAt(i));
                lineBuilder.append(cp.getPatternLines()[row]).append("  "); // small gap
            }
            System.out.println(lineBuilder.toString());
        }
    }

    public static void main(String[] args) {
        String word = "OOPS";
        printBanner(word);
    }
}