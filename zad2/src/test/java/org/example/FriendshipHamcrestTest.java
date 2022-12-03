package org.example;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class FriendshipHamcrestTest {
    Friendships friends = new Friendships();

    @Test
    public void testFriendshipTrue() {
        friends.addFriend("Joe", "Matt");
        assertThat(friends.areFriends("Joe", "Matt"), is(true));
    }

    @Test
    public void testFriendshipFalse() {
        friends.addFriend("Joe", "Tom");
        assertThat(friends.areFriends("Joe", "Matt"), not(true));
    }

    @Test
    public void testOtherFriend() {
        friends.addFriend("Joe", "Tom");
        assertThat(friends.getFriendsList("Joe"), hasItem("Tom"));
    }

    @Test
    public void testFriendsNumber() {
        friends.addFriend("Joe", "Tom");
        friends.addFriend("Joe", "Matt");
        assertThat(friends.getFriendsList("Joe"), hasItems("Tom", "Matt"));
    }

    @Test
    public void testMakeFriends() {
        friends.makeFriends("Joe", "Tom");
        friends.makeFriends("Joe", "Matt");
        assertThat(friends.getFriendsList("Joe"), hasItems("Tom", "Matt"));
    }

    @Test
    public void testMakeFriendsOneSide() {
        friends.makeFriends("Joe", "Tom");
        assertThat(friends.getFriendsList("Tom"), hasItems("Joe"));
    }

    @Test
    public void testAddFriendTwice() {
        friends.addFriend("Joe", "Tom");
        friends.addFriend("Joe", "Tom");
        assertThat(friends.getFriendsList("Joe"), hasSize(2));
    }

    @Test
    public void testDuplicateTom() {
        friends.makeFriends("Joe", "Tom");
        friends.makeFriends("Tom", "Joe");
        List<String> expected = new ArrayList<>();
        expected.add("Tom");
        expected.add("Tom");
        assertThat(friends.getFriendsList("Joe"), equalTo(expected));
    }
}
