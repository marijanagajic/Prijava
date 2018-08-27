/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;


/**
 *
 * @author SVETLANA.LOKAL
 */
public class ElasticSearch {

//    Client client = TransportClient.builder().build()
//            .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

//    RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
//            new HttpHost("localhost", 9200, "http")).build());
//    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//    searchSourceBuilder.query (QueryBuilders.matchAllQuery
//
//    ());            
//    searchSourceBuilder.aggregation (AggregationBuilders.terms
//    ("top_10_states").field("state").size(10));
//SearchRequest searchRequest = new SearchRequest();
//
//    searchRequest.indices (
//
//    "social-*");
//    searchRequest.source (searchSourceBuilder);
//    SearchResponse searchResponse = client.search(searchRequest);
}
