package kgarrison.doingsplits.viewModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class LeaderboardContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<LeaderboardItem> ITEMS = new ArrayList<LeaderboardItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, LeaderboardItem> ITEM_MAP = new HashMap<String, LeaderboardItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(LeaderboardItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static LeaderboardItem createDummyItem(int position) {
        return new LeaderboardItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class LeaderboardItem {
        public final String id;
        public final String content;
        public final String details;

        public LeaderboardItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
