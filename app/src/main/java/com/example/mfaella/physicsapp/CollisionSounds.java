package com.example.mfaella.physicsapp;

import android.util.SparseArray;

import com.badlogic.androidgames.framework.Audio;
import com.badlogic.androidgames.framework.Sound;
import com.example.mfaella.physicsapp.gameObjects.DynamicJointGO;
import com.example.mfaella.physicsapp.gameObjects.EnclosureGO;


/**
 * Created by mfaella on 04/03/16.
 */
public class CollisionSounds {
    private static Sound metallicSound;
    private static Sound dumbSound;

    private static SparseArray<Sound> map;

    private static int myHash(Class<?> a, Class<?> b)
    {
        return a.hashCode() ^ b.hashCode();
    }

    public static void init(Audio audio)
    {
        metallicSound = audio.newSound("urto1.wav");
        dumbSound = audio.newSound("urto2.wav");
        map = new SparseArray<>();

        map.put(myHash(DynamicJointGO.class, DynamicJointGO.class), metallicSound);
        map.put(myHash(DynamicJointGO.class, EnclosureGO.class), dumbSound);
    }

    public static Sound getSound(Class<?> a, Class<?> b)
    {
        int hash = myHash(a, b);
        return map.get(hash);
    }
}
