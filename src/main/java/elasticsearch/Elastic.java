/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticsearch;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;

/**
 *
 * @author SVETLANA.LOKAL
 */
public class Elastic {
    
//    //Create Client
//Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "localtestsearch").build();
//TransportClient transportClient = new TransportClient(settings);
//transportClient = transportClient.addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
//return (Client) transportClient;
//
////Create Index and set settings and mappings
//
//CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate(indexName);
//createIndexRequestBuilder.execute().actionGet();
//
////Add documents
//IndexRequestBuilder indexRequestBuilder = client().prepareIndex(indexName, documentType, documentId);
////build json object
//XContentBuilder contentBuilder = jsonBuilder().startObject().prettyPrint();
//contentBuilder.field("name", "jai");
//contentBuilder.stopObject();
//indexRequestBuilder.setSource(contentBuilder);
//IndexResponse response = indexRequestBuilder .execute().actionGet();
//
////Get document
//GetRequestBuilder getRequestBuilder = client().prepareGet(indexName, type, id);
//getRequestBuilder.setFields(new String[]{"name"});
//GetResponse response = getRequestBuilder.execute().actionGet();
//String name = response.field("name").getValue().toString();
}
