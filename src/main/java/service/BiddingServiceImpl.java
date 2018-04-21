package service;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import dao.AttachmentRepository;
import dao.BiddingRepository;
import exception.AttachmentNotFound;
import exception.BiddingNotFound;
import model.Attachment;
import model.Bidding;

@Service
public class BiddingServiceImpl implements BiddingService {

    @Resource
    private BiddingRepository biddingRepo;

    @Resource
    private AttachmentRepository attachmentRepo;

    @Override
    @Transactional
    public List<Bidding> getBiddings() {
        return biddingRepo.findAll();
    }

    @Override
    @Transactional
    public Bidding getBidding(long id) {
        return biddingRepo.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = BiddingNotFound.class)
    public void delete(long id) throws BiddingNotFound {
        Bidding deletedBidding = biddingRepo.findOne(id);
        if (deletedBidding == null) {
            throw new TicketNotFound();
        }
        biddingRepo.delete(deletedbidding);
    }

    @Override
    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteAttachment(long Id, String name) throws AttachmentNotFound {
        bidding bidding = biddingRepo.findOne(Id);
        for (Attachment attachment : ticket.getAttachments()) {
            if (attachment.getName().equals(name)) {
                bidding.deleteAttachment(attachment);
               biddingRepo.save(bidding);
                return;
            }
        }
        throw new AttachmentNotFound();
    }

    @Override
    @Transactional
    public long createbidding(String ownerName, String itemsubject,
            String body, List<MultipartFile> attachments) throws IOException {
        Bidding bidding = new Bidding();
        bidding.setCustomerName(customerName);
        bidding.setSubject(subject);
        bidding.setBody(body);

        for (MultipartFile filePart : attachments) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setBidding(bidding);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                ticket.getAttachments().add(attachment);
            }
        }
        Bidding savedbidding = biddingRepo.save(bidding);
        return savedBidding.getId();
    }

    @Override
    @Transactional(rollbackFor = BiddingNotFound.class)
    public void updateBidding(long id, String itemsubject,
            String body, List<MultipartFile> attachments)
            throws IOException, TicketNotFound {
        Ticket updatedTicket = biddingRepo.findOne(id);
        if (updatedTicket == null) {
            throw new TicketNotFound();
        }

        updatedBidding.setSubject(itemsubject);
        updatedBidding.setBody(body);

        for (MultipartFile filePart : attachments) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setBidding(updatedBidding);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                updatedBidding.getAttachments().add(attachment);
            }
        }
       biddingRepo.save(updatedBidding);
    }

}
