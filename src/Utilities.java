import java.util.Map;
import java.util.TreeMap;

public class Utilities {
    /**
     * Unmutable treemap of MIDI notes without octaves.
     */
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
