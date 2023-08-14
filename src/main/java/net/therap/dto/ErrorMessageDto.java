package net.therap.dto;

import java.util.List;

/**
 * @author tanvirtareq
 * @since 8/14/23
 */
public class ErrorMessageDto {
    List<ButtonDto> buttonList;
    private String message;

    public ErrorMessageDto(String message, List<ButtonDto> buttonList) {
        this.message = message;
        this.buttonList = buttonList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ButtonDto> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<ButtonDto> buttonList) {
        this.buttonList = buttonList;
    }
}
