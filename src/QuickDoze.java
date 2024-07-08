public class QuickDoze {
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
         * 
         * another another note - even though theres only four base arguments, utau will
         * actually only ever render with either 12 or 13 (????). if it is rendering
         * with 13 args it means the flags exist, and if its only 12, then the flags
         * argument is omitted and the rest of the indexes move up one
         */

        int argIndex = args.length;
        boolean flagMode = false;

        // Catch if the user tries to render without the required four arguments passed
        // from UTAU. This shouldn't happen unless the user is using the resampler
        // outside of UTAU.
        if (argIndex < 4) {
            throw new BadRenderInputException("not enough render parameters!");
        } else if (argIndex == 12) {
            flagMode = false;
        } else if (argIndex == 13) {
            flagMode = true;
        } else {
            throw new BadRenderInputException("too many render parameters!");
        }

        // load defaults
        String inputFile = args[0];
        String outputFile = args[1];
        String note = args[2];
        float velocity = Integer.valueOf(args[3]);
        String flags = "";
        float offset = 0;
        int stationaryLength = 1000;
        float consonantLength = 0;
        float cutoff = 0;
        float volume = 100;
        float modulation = 0;
        String tempo = "!120";
        String pitchInput = "AA";

        if (flagMode) {
            flags = args[4];
            offset = Float.valueOf(args[5]);
            stationaryLength = Integer.valueOf(args[6]);
            consonantLength = Float.valueOf(args[7]);
            cutoff = Float.valueOf(args[8]);
            volume = Float.valueOf(args[9]);
            modulation = Float.valueOf(args[10]);
            tempo = args[11];
            pitchInput = args[12];
        } else {
            offset = Float.valueOf(args[4]);
            stationaryLength = Integer.valueOf(args[5]);
            consonantLength = Float.valueOf(args[6]);
            cutoff = Float.valueOf(args[7]);
            volume = Float.valueOf(args[8]);
            modulation = Float.valueOf(args[9]);
            tempo = args[10];
            pitchInput = args[11];
        }

        System.out.println("inputFile: " + inputFile);
        System.out.println("outputFile " + outputFile);
        System.out.println("note: " + note);
        System.out.println("velocity: " + velocity);
        System.out.println("flags: " + flags);
        System.out.println("offset: " + offset);
        System.out.println("stationaryLength: " + stationaryLength);
        System.out.println("consonantLength: " + consonantLength);
        System.out.println("cutoff: " + cutoff);
        System.out.println("volume: " + volume);
        System.out.println("modulation: " + modulation);
        System.out.println("tempo: " + tempo);
        System.out.println("pitchInput: " + pitchInput);
        System.in.read();
    }

}
