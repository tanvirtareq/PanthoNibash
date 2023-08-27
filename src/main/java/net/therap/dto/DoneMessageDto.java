package net.therap.dto;

import java.util.List;

/**
 * @author tanvirtareq
 * @since 8/14/23
 */
public class DoneMessageDto {

    private List<ButtonDto> buttonList;
    private String message;
    private String header;

    public DoneMessageDto(String message, List<ButtonDto> buttonList, String header) {
        this.message = message;
        this.buttonList = buttonList;
        this.header = header;
    }

    public List<ButtonDto> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<ButtonDto> buttonList) {
        this.buttonList = buttonList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
