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
import exception.BiddingItemNotFound;
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
    public List<Bidding> getBidding() {
        return biddingRepo.findAll();
    }

    @Override
    @Transactional
    public Bidding getBIdding(long id) {
        return biddingRepo.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = BiddingItemNotFound.class)
    public void delete(long id) throws BiddingItemNotFound {
        Bidding deletedBiddingItem = biddingRepo.findOne(id);
        if (deletedBiddingItem == null) {
            throw new BiddingItemNotFound();
        }
        biddingRepo.delete(deletedBiddingItem);
    }

    @Override
    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteAttachment(long itemId, String name) throws AttachmentNotFound {
        Bidding item = biddingRepo.findOne(itemId);
        for (Attachment attachment : item.getAttachments()) {
            if (attachment.getName().equals(name)) {
                ticket.deleteAttachment(attachment);
                ticketRepo.save(item);
                return;
            }
        }
        throw new AttachmentNotFound();
    }

    @Override
    @Transactional
    public long createTicket(String customerName, String subject,
            String body, List<MultipartFile> attachments) throws IOException {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(customerName);
        ticket.setSubject(subject);
        ticket.setBody(body);

        for (MultipartFile filePart : attachments) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setTicket(ticket);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                ticket.getAttachments().add(attachment);
            }
        }
        Ticket savedTicket = ticketRepo.save(ticket);
        return savedTicket.getId();
    }

    @Override
    @Transactional(rollbackFor = BiddingItemNotFound.class)
    public void updateTicket(long id, String subject,
            String body, List<MultipartFile> attachments)
            throws IOException, TicketNotFound {
        Bidding updatedTicket = biddingRepo.findOne(id);
        if (updatedTicket == null) {
            throw new BiddingItemNotFound();
        }

        updatedTicket.setSubject(subject);
        updatedTicket.setBody(body);

        for (MultipartFile filePart : attachments) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setBidding(updatedTicket);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                updatedTicket.getAttachments().add(attachment);
            }
        }
        biddingRepo.save(updatedTicket);
    }

}
