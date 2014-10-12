package org.whs.Tournament.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UserInputReader {
    private String outputMessage;
    private String outputMessagePrepend;

    public UserInputReader(String message) {
        outputMessage = message;
    }

    private BufferedReader getInputBuffer() {
        InputStreamReader inputReader = new InputStreamReader(System.in);
        return new BufferedReader(inputReader);
    }

    private String capture() {
        BufferedReader inputBuffer = getInputBuffer();
        try {
            String message = getMessage();
            System.out.print(message);
            return inputBuffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int captureInteger() {
        String capturedString = capture();
        return Integer.parseInt(capturedString);
    }

    public String captureString() {
        return capture();
    }

    public void setMessagePrepend(String prepend) {
        outputMessagePrepend = prepend;
    }

    private String getMessage() {
        return outputMessage + outputMessagePrepend;
    }


}