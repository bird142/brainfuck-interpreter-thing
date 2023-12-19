import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        OutputStream out = System.out;
        char[] code = "".toCharArray();
        byte[] memory = new byte[30000];
        int codePointer = 0;
        int memPointer = 0;
        while (codePointer < code.length) {
            switch (code[codePointer]) {
                case '+':
                    memory[memPointer]++;
                    break;
                case '-':
                    memory[memPointer]--;
                    break;
                case '>':
                    memPointer++;
                    break;
                case '<':
                    memPointer--;
                    break;
                case '.':
                    out.write((char)memory[memPointer]);
                    out.flush();
                    break;
                case ',':
                    memory[memPointer] = (byte)in.read();
                    break;
                case '[':
                    if (memory[memPointer] == 0) {
                        int loopDepth = 1;
                        while (loopDepth > 0) {
                            codePointer++;
                            if (codePointer > code.length)
                                throw new ArrayIndexOutOfBoundsException();
                            if (code[codePointer] == '[')
                                loopDepth++;
                            if (code[codePointer] == ']')
                                loopDepth--;
                        }
                    }
                    break;
                case ']':
                    if (memory[memPointer] != 0) {
                        int loopDepth = 1;
                        while (loopDepth > 0) {
                            codePointer--;
                            if (codePointer < 0)
                                throw new ArrayIndexOutOfBoundsException();
                            if (code[codePointer] == ']')
                                loopDepth++;
                            if (code[codePointer] == '[')
                                loopDepth--;
                        }
                    }
                    break;
            }
            codePointer++;
        }
    }
}
