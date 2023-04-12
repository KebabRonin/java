package org.example;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class SharedMemory {
    CopyOnWriteArrayList<Token> tokenList = new CopyOnWriteArrayList<>();

    public SharedMemory(int n) {
        List<Token> randomNumbers = ThreadLocalRandom.current()
                .ints(1, (int) Math.pow(n, 3))
                .parallel()
                .limit((long) n * n * n)
                .mapToObj(Token::new).collect(Collectors.toList());
        tokenList.addAll(randomNumbers);
    }

    public synchronized List<Token> extractTokens(int howMany) {
        List<Token> extracted = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < howMany; i++) {
            if (tokenList.isEmpty()) {
                break;
            }
            int index = rand.nextInt(tokenList.size());
            extracted.add(tokenList.get(index));
            tokenList.remove(index);
        }
        return extracted;
    }
}