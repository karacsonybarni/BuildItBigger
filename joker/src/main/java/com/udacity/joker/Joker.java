package com.udacity.joker;

import java.util.Random;

public class Joker {

    private final String[] jokes = {
            "Why don't blind people skydive?\nBecause it scares the crap out of their dogs.",
            "Google request:\n\nHow to disable autocorrect in wife?",
            "I used to breed rabbits. Then I realized they can handle it themselves.",
            "What do you call the soft tissue between a shark's teeth?\n\nA slow swimmer.",
            " - Mom! I’m a 3d printer!\n\n - Oh come on, Tommy, close the door when you poop.",
            "Losing a wife can be very tough. Some may even say impossible.",
            "What swims and starts with a T?\n\nTwo ducks."
    };

    private Random indexProvider = new Random();

    public String getJoke() {
        int i = indexProvider.nextInt(jokes.length);
        return jokes[i];
    }
}
