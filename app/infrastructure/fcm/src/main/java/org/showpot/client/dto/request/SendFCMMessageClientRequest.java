package org.showpot.client.dto.request;

import java.util.List;
import org.showpot.client.dto.param.MessageFCMClientParam;

public record SendFCMMessageClientRequest(
    MessageFCMClientParam message,
    List<String> fcmTokens
) {

}
