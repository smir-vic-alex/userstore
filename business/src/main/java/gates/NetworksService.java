package gates;

import com.smirix.rest.elements.messages.MessageRq;
import com.smirix.rest.elements.messages.MessageRs;
import com.smirix.senders.BaseSender;
import gates.requests.NetworkEntityGateRq;
import gates.requests.NetworkEntityGateRs;

public class NetworksService {
    private BaseSender moduleSender;
    private String moduleUrl;

    public NetworksService(BaseSender moduleSender, String moduleUrl) {
        this.moduleSender = moduleSender;
        this.moduleUrl = moduleUrl;
    }

    public String getAuthRedirectUrl(String networkType) {
        NetworkEntityGateRq rq = new NetworkEntityGateRq();
        rq.setType(networkType);

        MessageRq<NetworkEntityGateRq> messageRq = new MessageRq<>();
        messageRq.setBody(rq);
        messageRq.setUrl(moduleUrl);

        MessageRs<NetworkEntityGateRs> rs = moduleSender.send(messageRq);

        checkSuccess(rs);

        return rs.getBody().getAuthRedirectUrl();
    }

    private void checkSuccess(MessageRs response) {
        if (!response.getHead().getSuccess())
            throw new RuntimeException();
    }
}
