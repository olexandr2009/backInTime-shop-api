package ua.shop.backintime.user.service.exception;

import java.util.UUID;

public class NoteNotFoundException extends Exception {

    private static final String NOTE_NOT_FOUND_EXCEPTION_TEXT = "Note with id = %s not found.";
    private static final String CAN_NOT_UPDATE_NOTE_WITHOUT_ID_EXCEPTION_TEXT = "Can not found note without id.";

    public NoteNotFoundException() {
        super(CAN_NOT_UPDATE_NOTE_WITHOUT_ID_EXCEPTION_TEXT);
    }

    public NoteNotFoundException(UUID noteId) {
        super(String.format(NOTE_NOT_FOUND_EXCEPTION_TEXT, noteId));
    }
}