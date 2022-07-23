package com.example.phobo.placeholder;

import com.example.phobo.model.Photographer;

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
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<Photographer> ITEMS = new ArrayList<Photographer>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, Photographer> ITEM_MAP = new HashMap<String, Photographer>();

//    private static final int COUNT = 25;
//
//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createPlaceholderItem(i));
//        }
//    }

    private static void addItem(Photographer item) {
        ITEMS.add(item);
//        ITEM_MAP.put(item.getId()+"", item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}