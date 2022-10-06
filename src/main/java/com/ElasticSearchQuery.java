package com;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import com.Model.Xml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ElasticSearchQuery {

    private final String indexName = "xmllocal";
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public String createDocument(Xml xml) {
        IndexResponse response = null;
        try {
            response = elasticsearchClient
                    .index(i -> i
                            .index(indexName)
                            .id(String.valueOf(new Date().getTime()))
                            .document(xml));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(response.version());
    }
    public List<Xml> searchAllDocuments() throws IOException {

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse<Xml> searchResponse = elasticsearchClient.search(searchRequest, Xml.class);
        List<Hit<Xml>> hits = searchResponse.hits().hits();
        List<Xml> list = new ArrayList<>();
        for (Hit<Xml> object : hits) {

            System.out.print((object.source()));
            list.add(object.source());

        }
        return list;
    }
    public List<Hit<Xml>> searchQuery(String field) throws IOException {
        String searchText = field;
        SearchResponse<Xml> response = elasticsearchClient.search(s -> s
                        .index(indexName)
                        .query(q -> q
                                .match(t -> t
                                        .field("title")
                                        .query(searchText)
                                )
                        ),
                Xml.class
        );

        TotalHits total = response.hits().total();
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

        if (isExactResult) {
            System.out.println("There are " + total.value() + " results");
        } else {
            System.out.println("There are more than " + total.value() + " results");
        }

        List<Hit<Xml>> hits = response.hits().hits();
        for (Hit<Xml> hit : hits) {
            Xml xml = hit.source();
            System.out.println("Found found  " + xml.getDescription() + ", score " + hit.score());
        }


        return hits;
    }

}