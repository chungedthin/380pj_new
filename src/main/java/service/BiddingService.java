package service;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import exception.AttachmentNotFound;
import exception.BiddingItemNotFound;
import model.Bidding;

public interface BiddingService {

    public long createBidding(String customerName, String subject,
            String body, List<MultipartFile> attachments) throws IOException;

    public List<Bidding> getBidding();

    public Bidding getBidding(long id);

    public void updateBidding(long id, String subject,
            String body, List<MultipartFile> attachments)
            throws IOException, BiddingItemNotFound;

    public void delete(long id) throws BiddingItemNotFound;

    public void deleteAttachment(long ticketId, String name)
            throws AttachmentNotFound;
}
