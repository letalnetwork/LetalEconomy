package dev.loststr1ng.letaleconomy.storage;

import java.util.UUID;
import dev.loststr1ng.letaleconomy.model.PlayerData;
import dev.loststr1ng.letaleconomy.entity.AbstractEntity;

public abstract class Storage {

    public abstract void setup();

    public abstract PlayerData loadOrCreate(AbstractEntity entity);

    public abstract void loadAll();

    public abstract void save(PlayerData playerData);

    public abstract void reload();
}
