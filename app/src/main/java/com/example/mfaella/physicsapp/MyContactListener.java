package com.example.mfaella.physicsapp;

import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.google.fpl.liquidfun.Body;
import com.google.fpl.liquidfun.Contact;
import com.google.fpl.liquidfun.ContactListener;
import com.google.fpl.liquidfun.Fixture;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by mfaella on 01/03/16.
 */
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

    /** Warning: this method runs inside world.step
     *  Hence, it cannot change the physical world.
     */
    @Override
    public void beginContact(Contact contact) {
        //Log.d("MyContactListener", "Begin contact");
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

        // TO DO: use an object pool instead
        cache.add(new Collision(a, b));

        // Sound sound = CollisionSounds.getSound(a.getClass(), b.getClass());
        //if (sound!=null)
        //    sound.play(0.7f);
        // Log.d("MyContactListener", "contact bwt " + a.name + " and " + b.name);
    }
}
