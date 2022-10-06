package com;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.Model.Xml;
import com.XMLparser.XMLParserRss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ElasticSearchController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;
    @Autowired
    private XMLParserRss xmlParserRss;
    private Xml xml;


    @PostMapping("/createDocument")
    public ResponseEntity<Object> createOrUpdateDocument() throws IOException {
        List<Xml> listXml = xmlParserRss.getlist();
        for (Xml xml:listXml) {
            elasticSearchQuery.createDocument(xml);
            System.out.println(xml.getTitle());
        }
        return new ResponseEntity<>("created", HttpStatus.OK);
    }

    @GetMapping("/searchQuery")
    public ResponseEntity<Object> searchQuery(@RequestParam String field) throws IOException {
        List<Hit<Xml>> hits = elasticSearchQuery.searchQuery(field);
        for (Hit<Xml> hit : hits) {
            Xml xml = hit.source();
            return new ResponseEntity<>("Found found  " + xml+ ", score " + hit.score(), HttpStatus.OK);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }



    @GetMapping("/getAllDocument")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<Xml> xml = elasticSearchQuery.searchAllDocuments();
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }
}
