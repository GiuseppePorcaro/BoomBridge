package com.example.mfaella.physicsapp;

import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.google.fpl.liquidfun.Body;
import com.google.fpl.liquidfun.Contact;
import com.google.fpl.liquidfun.ContactListener;
import com.google.fpl.liquidfun.Fixture;

import java.util.Collection;
import java.util.HashSet;

public class MyContactListener extends ContactListener {

    GameWorld gw;

    public MyContactListener(GameWorld gw) {
        this.gw = gw;
    }

    private Collection<Collision> cache = new HashSet<>();

    public Collection<Collision> getCollisions() {
        Collection<Collision> result = new HashSet<>(cache);
        cache.clear();
        return result;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA(),
                fb = contact.getFixtureB();
        Body ba = fa.getBody(), bb = fb.getBody();
        Object userdataA = ba.getUserData(), userdataB = bb.getUserData();
        GameObject a = (GameObject)userdataA,
                   b = (GameObject)userdataB;

        String nameA = a.getName();
        String nameB = b.getName();
        if((nameA.contains("ROAD")&&nameB.contains("Enclosure"))||(nameB.contains("ROAD")&&nameA.contains("Enclosure"))){
            gw.setPlayerHasLost(true);
        }
        cache.add(new Collision(a, b));
    }
}
