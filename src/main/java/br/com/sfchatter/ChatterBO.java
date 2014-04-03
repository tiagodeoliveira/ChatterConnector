package br.com.sfchatter;

import br.com.sfchatter.jsonObjects.*;
import br.com.sfchatter.jsonObjects.segments.Segment;
import br.com.sfchatter.utils.StringUtils;
import com.google.gson.*;
import org.apache.commons.lang.RandomStringUtils;

import java.io.*;
import java.util.*;

public class ChatterBO {

    private Connector sfChatterConnector;

    public ChatterBO() {
    }

    public Connector getSfChatterConnector() {
        if (this.sfChatterConnector == null) {
            try {
                this.sfChatterConnector = new Connector();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.sfChatterConnector;
    }

    public String getStatistics() {
        String result = "";

        Boolean forceRefresh = true;

        System.out.println("Getting groups from user");
        List<GroupDetails> groups = getMyGroups(forceRefresh);

        Map<GroupDetails, Integer> groupsActivity = new HashMap<GroupDetails, Integer>();
        Map<FeedItem, Integer> likedItems = new HashMap<FeedItem, Integer>();
        Map<String, Integer> topHashTags = new HashMap<String, Integer>();

        List<FeedItem> allPosts = new ArrayList<FeedItem>();
        for (GroupDetails group : groups) {
            List<FeedItem> posts = getAllPosts("chatter/feeds/record/" + group.getId() + "/feed-items", forceRefresh);

            for (FeedItem item : posts) {
                likedItems.put(item, item.getLikes().getTotal());
                Set<String> hashTags = StringUtils.getHashTagsOnText(item.getBody().getText());
                for (String tag : hashTags) {
                    Integer tagCount = 1;
                    if (topHashTags.containsKey(tag)) {
                        tagCount += topHashTags.get(tag);
                    }
                    topHashTags.put(tag, tagCount);
                }
            }
            groupsActivity.put(group, posts.size());
        }

        Map<GroupDetails, Integer> sorted = (Map<GroupDetails, Integer>) this.sortByComparator(groupsActivity);
        for (Map.Entry<GroupDetails, Integer> entry : sorted.entrySet()) {
            System.out.println(entry.getKey().getName() + " has " + entry.getValue());
        }

        Map<FeedItem, Integer> sortedl = (Map<FeedItem, Integer>) this.sortByComparator(likedItems);
        for (Map.Entry<FeedItem, Integer> entry : sortedl.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey().getId() + " has " + entry.getValue() + " likes ");
            }
        }

        for(Map.Entry<String, Integer> entry: topHashTags.entrySet()){
            if(entry.getValue() > 1){
                System.out.println(entry.getKey() + " has " + entry.getValue());
            }
        }
        return result;
    }


    private Map sortByComparator(Map unsortMap) {

        List list = new LinkedList(unsortMap.entrySet());

        // sort list based on comparator
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // put sorted list into map again
        //LinkedHashMap make sure order in which keys were inserted
        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public List<GroupDetails> getMyGroups(Boolean forceRefresh) {
        List<GroupDetails> result = null;
        File file = new File("allGroups.dat");
        if (file.exists() && !forceRefresh) {
            try {
                FileInputStream is = new FileInputStream(file);
                ObjectInputStream os = new ObjectInputStream(is);
                result = (List<GroupDetails>) os.readObject();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = new ArrayList<GroupDetails>();
            String response = this.getSfChatterConnector().executeGETMethod("chatter/users/me/groups");
            Map<String, String> map = new Gson().fromJson(response, Map.class);
            JsonElement el = new Gson().toJsonTree(map.get("groups"));
            if (el.isJsonArray()) {
                JsonArray arr = el.getAsJsonArray();
                for (int i = 0; i < arr.size(); i++) {
                    GroupDetails group = new Gson().fromJson(arr.get(i), GroupDetails.class);
                    result.add(group);
                }
            }
            try {
                if (file.exists()) {
                    file.delete();
                }
                FileOutputStream os = new FileOutputStream(file);
                ObjectOutputStream oo = new ObjectOutputStream(os);
                oo.writeObject(result);
                oo.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void randomLike(Integer howManyGroups, Integer howManyPosts) {
        List<GroupDetails> groups = this.getMyGroups(false);
        howManyGroups = (howManyGroups > groups.size() - 1 ? groups.size() - 1 : howManyGroups);

        Random random = new Random();
        Integer count = 0;
        do {
            Integer randomInt = random.nextInt(groups.size() - 1);
            GroupDetails group = groups.get(randomInt);
            System.out.println("On group " + group.getName());
            List<FeedItem> posts = this.getAllPosts("chatter/feeds/record/" + group.getId() + "/feed-items", false);
            for (int i = 0; i < howManyPosts; i++) {
                Integer randomPosts = random.nextInt(posts.size() - 1);
                this.likeAPost(posts.get(randomPosts).getId());
            }
            count++;
        } while (count < howManyGroups);

    }

    public void randomPost(Integer howManyGroups, Integer howManyPosts) {
        Integer cont = 0;

        List<GroupDetails> groups = this.getMyGroups(false);
        howManyGroups = (howManyGroups > groups.size() - 1 ? groups.size() - 1 : howManyGroups);
        Random random = new Random();
        Integer count = 0;
        do {
            Integer randomInt = random.nextInt(groups.size() - 1);
            GroupDetails group = groups.get(randomInt);
            System.out.println("On group " + group.getName());
            for (int i = 0; i < howManyPosts; i++) {
                String hashTag = "#" + RandomStringUtils.randomAlphabetic(4);

                FeedItemInput item = new FeedItemInput();
                MessageBodyInput msg = new MessageBodyInput();
                Segment[] post = new Segment[1];

                post[0] = new Segment();
                post[0].setText("Random post generate for Code.a.thon event!!" + hashTag);
                post[0].setType("Text");
                msg.setMessageSegments(post);
                item.setBody(msg);

                String groupId = group.getId();
                this.getSfChatterConnector().executePOSTMethod("chatter/feeds/record/" + groupId + "/feed-items", new Gson().toJson(item));
                cont++;
            }
            count++;
        } while (count < howManyGroups);

        System.out.println("Generate " + cont + " random posts!");
    }

    private List<FeedItem> getAllPosts(String url, Boolean forceRefresh) {
        System.out.println("Listing posts from group... " + url);
        List<FeedItem> result = new ArrayList<FeedItem>();
        File file = new File("allPosts.dat");
        if (file.exists() && !forceRefresh) {
            try {
                FileInputStream is = new FileInputStream(file);
                ObjectInputStream os = new ObjectInputStream(is);
                result = (List<FeedItem>) os.readObject();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            String responseStr = this.getSfChatterConnector().executeGETMethod(url);

            JsonParser parser = new JsonParser();
            JsonElement pageItems = parser.parse(responseStr);
            JsonObject pageObject = pageItems.getAsJsonObject();

            JsonArray itemsArr = pageObject.get("items").getAsJsonArray();
            Iterator<JsonElement> iterator = itemsArr.iterator();
            while (iterator.hasNext()) {
                JsonObject element = iterator.next().getAsJsonObject();
                FeedItem item = new FeedItem();
                item.setActor(element.get("actor").toString());
                item.setBody(new Gson().fromJson(element.get("body"), Segment.class));
                item.setClientInfo(new Gson().fromJson(element.get("clientInfo"), ClientInfo.class));
                item.setParent(new Gson().fromJson(element.get("parent"), GroupDetails.class));
                item.setLikes(new Gson().fromJson(element.get("likes"), LikePage.class));
                item.setCreatedDate(element.get("createdDate").toString());
                item.setId(element.get("id").getAsString());
                item.setModifiedDate(element.get("modifiedDate").toString());
                result.add(item);
            }

            if (!(pageObject.get("nextPageUrl") instanceof JsonNull)) {
                String nextPageURL = pageObject.get("nextPageUrl").getAsString();
                result.addAll(getAllPosts(nextPageURL, true));
            }
            try {
                if (file.exists()) {
                    file.delete();
                }
                FileOutputStream os = new FileOutputStream(file);
                ObjectOutputStream oo = new ObjectOutputStream(os);
                oo.writeObject(result);
                oo.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private void likeAPost(String postId) {
        String resp = this.getSfChatterConnector().executePOSTMethod("chatter/feed-items/" + postId + "/likes", "");
    }

}
