package model;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

public class Bidding {

    private long id;
    private String ownerName;
    private String itemSubject;
    private String body;
    private String itemContent;
    private int bids;
    private String status;
    private String comment;
    private long price;
    private Map<String, Attachment> attachments = new Hashtable<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    
    public String getOwnerName() {
        return ownerName;
    }

    public void setCustomerName(String customerName) {
        this.ownerName = ownerName;
    }

    public String getItemSubject() {
        return itemSubject;
    }

    public void setItemSubject(String subject) {
        this.itemSubject = itemSubject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public String getItemContent() {
        return itemContent;
    }
    
    public void setItemContent(String itemcontent) {
        this.itemContent = itemContent;
    }
    
      public long getbids() {
        return bids;
    }

    public void setbids(int bids) {
        this.bids = bids;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String comment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Attachment getAttachment(String name) {
        return this.attachments.get(name);
    }
    
    public Collection<Attachment> getAttachments() {
        return this.attachments.values();
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.put(attachment.getName(), attachment);
    }

    public int getNumberOfAttachments() {
        return this.attachments.size();
    }
    
    public boolean hasAttachment(String name) {
        return this.attachments.containsKey(name);
    }

    public Attachment deleteAttachment(String name) {
        return this.attachments.remove(name);
    }    
}


