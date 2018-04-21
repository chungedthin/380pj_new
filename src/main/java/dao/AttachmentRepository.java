package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    public Attachment findByTicketIdAndName(long ticketId, String name);
}
