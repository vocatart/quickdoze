import java.util.Map;
import java.util.TreeMap;

public class quickdoze {
    public static void main(String[] args) throws Exception {
        /*
         * utau passes the following 13 arguments in order:
         * 1 - input file dir (directory of the queried sample)
         * 2 - output file dir (directory of utau temp export)
         * 3 - note name (note in sharp notation)
         * 4 - velocity (velocity of the diphone)
         * 
         * the following flags are optional
         * 
         * 5 - flags (string of all flags as presented in utau)
         * 6 - preutterance (render offset from the start of the audio)
         * 7 - stationary (length of the stretched note portion in ms)
         * 8 - diphone (length of the un-stretched note portion in ms)
         * 9 - cutoff (render offset from the end of the audio)
         * 10 - volume (percentage volume of the final render)
         * 11 - modulation (percentage of the true f0 used)
         * 12 - tempo (bpm of the track in the format !bpm)
         * 13 - pitch (base64 pitch object in rle) (thank you uta)
         * 
         * note - utau doesnt really use 'stationaries' or 'diphones' in the commonly
         * accepted sense, it's just easier for me to understand like that.
         * 
         * another note - utau will not send default values for the optional flags if
         * they are not used!
         */
    }

    private static final Map<String, Integer> BASE_MIDI_NOTES = new TreeMap<String, Integer>() {
        {
            put("C", 0);
            put("C#", 1);
            put("D", 2);
            put("D#", 3);
            put("E", 4);
            put("F", 5);
            put("F#", 6);
            put("G", 7);
            put("G#", 8);
            put("A", 9);
            put("A#", 10);
            put("B", 11);
        }
    };

    /**
     * Checks to see if the input note name contains a negative octave.
     * 
     * @param noteName input note
     * @return <code>true</code> if the octave is negative, <code>false</code> if
     *         not.
     */
    private static boolean checkOctave(String noteName) {
        if (noteName.contains("-")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Parses note name into string array containing note and octave.
     * 
     * @param noteName input note
     * @return String array with index <code>0</code> being the note, and
     *         <code>1</code> being the octave.
     */
    private static String[] parseNoteName(String noteName) {
        StringBuilder note = new StringBuilder();
        StringBuilder octave = new StringBuilder();

        for (char token : noteName.toCharArray()) {
            if (Character.isDigit(token)) {
                octave.append(token);
            } else if (token == '-') {
                continue;
            } else {
                note.append(token);
            }
        }

        return new String[] {
                note.toString(),
                octave.toString()
        };
    }

    /**
     * Converts given note name (sharp-style) into MIDI note.
     * 
     * @param noteName input note
     * @return MIDI note number (C4 = 60)
     */
    public static int getTargetNote(String noteName) {
        String[] noteInfo = parseNoteName(noteName);

        int baseNote = BASE_MIDI_NOTES.get(noteInfo[0]);
        int noteOctave = Integer.valueOf(noteInfo[1]) + 1;

        if (checkOctave(noteName) == true) {
            return (noteOctave * 12 + baseNote) - 24;
        } else {
            return noteOctave * 12 + baseNote;
        }

    }

}
