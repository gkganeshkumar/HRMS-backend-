package com.example.Invoice_Hrms.service;
import java.util.List;
import java.util.stream.Collectors;

import com.example.Invoice_Hrms.dto.InvoiceDto;
import com.example.Invoice_Hrms.model.Invoice;
import com.example.Invoice_Hrms.model.Item;
import com.example.Invoice_Hrms.repository.InvoiceRepository;
import com.example.Invoice_Hrms.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class InvoiceService {



    private final InvoiceRepository invoiceRepository;
    private final ItemRepository itemRepository;


    public Invoice createInvoice(InvoiceDto dto) {
        Invoice invoice = toEntity(dto);
        Invoice savedInvoice = invoiceRepository.save(invoice);

        if (dto.getItems() != null && !dto.getItems().isEmpty()) {
            List<Item> items = dto.getItems().stream()
                    .map(i -> {
                        Item item = new Item();
                        item.setItemName(i.getItemName());
                        item.setQty(i.getQty());
                        item.setRate(i.getRate());
                        item.setAmount(i.getAmount());
                        item.setInvoiceId(savedInvoice.getId());
                        return item;
                    }).toList();

            itemRepository.saveAll(items);
        }

        return buildInvoiceItemData(savedInvoice);
    }


    private Invoice toEntity(InvoiceDto dto) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNo(dto.getInvoiceNo());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setInvoiceEmail(dto.getInvoiceEmail());
        invoice.setInvoiceCompanyName(dto.getInvoiceCompanyName());
        invoice.setCompanyAddress(dto.getCompanyAddress());
        invoice.setInvoicePinCode(dto.getInvoicePinCode());
        invoice.setInvoiceCountry(dto.getInvoiceCountry());
        invoice.setInvoiceMobileNo(dto.getInvoiceMobileNo());
        invoice.setCompanyMobileNo(dto.getCompanyMobileNo());
        invoice.setInvoiceConsultantName(dto.getInvoiceConsultantName());
        invoice.setInvoiceCurrency(dto.getInvoiceCurrency());
        invoice.setCompanyName(dto.getCompanyName());
        invoice.setCompanyAddress(dto.getCompanyAddress());
        invoice.setCompanyMobileNo(dto.getCompanyMobileNo());
        invoice.setCompanyEmail(dto.getCompanyEmail());
        invoice.setInvoiceTerms(dto.getInvoiceTerms());
        invoice.setDueDate(dto.getDueDate());
        invoice.setThanksNote(dto.getThanksNote());
        return invoice;
    }


    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(this::buildInvoiceItemData)
                .collect(Collectors.toList());
    }


    public Invoice getInvoiceById(String id) {
        return invoiceRepository.findById(id)
                .map(this::buildInvoiceItemData)
                .orElse(null);
    }


    private Invoice buildInvoiceItemData(Invoice invoice) {
        List<Item> items = itemRepository.findByInvoiceId(invoice.getId());
        invoice.setItems(items);
        return invoice;
    }
    public Invoice updateInvoice(String id, InvoiceDto dto) {
        return invoiceRepository.findById(id).map(existingInvoice -> {
            Invoice updatedInvoice = toEntity(dto);
            updatedInvoice.setId(id); // preserve ID


            Invoice savedInvoice = invoiceRepository.save(updatedInvoice);


            itemRepository.deleteAll(itemRepository.findByInvoiceId(id));

            if (dto.getItems() != null && !dto.getItems().isEmpty()) {
                List<Item> items = dto.getItems().stream()
                        .map(i -> {
                            Item item = new Item();
                            item.setItemName(i.getItemName());
                            item.setQty(i.getQty());
                            item.setRate(i.getRate());
                            item.setAmount(i.getAmount());
                            item.setInvoiceId(id);
                            return item;
                        }).toList();

                itemRepository.saveAll(items);
            }

            return buildInvoiceItemData(savedInvoice);
        }).orElse(null);
    }


    public boolean deleteInvoice(String id) {
        if (!invoiceRepository.existsById(id)) return false;

        invoiceRepository.deleteById(id);
        itemRepository.deleteAll(itemRepository.findByInvoiceId(id));
        return true;
    }






}