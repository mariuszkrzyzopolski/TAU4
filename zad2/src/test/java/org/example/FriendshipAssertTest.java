package org.example;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class FriendshipAssertTest {
    Friendships friends = new Friendships();

    @Test
    public void testIfTrueAddFriends() {
        friends.addFriend("Joe", "Matt");
        assertThat(friends.areFriends("Joe", "Matt")).isTrue();

    }

    @Test
    public void testIfAnotherFriend() {
        friends.addFriend("Joe", "Matt");
        assertThat(friends.areFriends("Joe", "Tom")).isFalse();
    }

    @Test
    public void testFriendsList() {
        friends.addFriend("Joe", "Tom");
        friends.addFriend("Joe", "Matt");

        assertThat(friends.getFriendsList("Joe")).hasSize(2).contains("Tom", "Matt");
    }

    @Test
    public void checkAddTheSameFriend() {
        friends.addFriend("Joe", "Tom");
        friends.addFriend("Joe", "Tom");

        assertThat(friends.getFriendsList("Joe")).contains("Tom").hasSize(2).doesNotContain("Matt");
    }

    @Test
    public void testMakeFriends() {
        friends.makeFriends("Joe", "Tom");
        assertThat(friends.getFriendsList("Tom")).asList().isNotNull().contains("Joe");
    }

    @Test
    public void testMakeFriendsOneSide() {
        friends.makeFriends("Joe", "Tom");
        assertThat(friends.getFriendsList("Tom")).asList().isNotNull().contains("Joe");
    }

    @Test
    public void testDuplicatedTom() {
        friends.makeFriends("Joe", "Tom");
        friends.makeFriends("Tom", "Joe");
        List<String> expected = new ArrayList<>();
        expected.add("Tom");
        expected.add("Tom");
        assertThat(friends.getFriendsList("Joe")).hasSameElementsAs(expected);
    }
}
