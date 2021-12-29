create table playable_characters (
    player_name VARCHAR(30) NOT NULL,
    PRIMARY KEY(player_name)
);
create table staffs (
    staff_id TINYINT NOT NULL,
    staff_name VARCHAR(30),
    staff_attack SMALLINT,
    staff_agility SMALLINT,
    PRIMARY KEY (staff_id)
);
create table robes (
    robe_id TINYINT NOT NULL,
    robe_name VARCHAR(30),
    robe_defense SMALLINT,
    robe_hp SMALLINT,
    PRIMARY KEY (robe_id)
);
create table rings (
    ring_id TINYINT NOT NULL,
    ring_name VARCHAR(30),
    ring_mane SMALLINT,
    ring_hp SMALLINT,
    PRIMARY KEY (ring_id)
);
create table abilities (
    ability_id TINYINT NOT NULL,
    ability_name VARCHAR(50),
    mana_cost TINYINT,
    level_req TINYINT,
    type VARCHAR(7),
    PRIMARY KEY (ability_id)
);
create table items (
    item_name VARCHAR(30) NOT NULL,
    craftable_staff_id TINYINT NOT NULL,
    craftable_robe_id TINYINT NOT NULL,
    craftable_ring_id TINYINT NOT NULL,
    PRIMARY KEY (item_name),
    FOREIGN KEY (craftable_staff_id) REFERENCES staffs(staff_id),
    FOREIGN KEY (craftable_robe_id) REFERENCES robes(robe_id),
    FOREIGN KEY (craftable_ring_id) REFERENCES rings(ring_id)
);
create table stats (
    stat_id TINYINT NOT NULL,
    level TINYINT,
    max_hp SMALLINT,
    max_mana SMALLINT,
    agility SMALLINT,
    attack SMALLINT,
    defense SMALLINT,
    xp_worth SMALLINT,
    item_drop_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (stat_id),
    FOREIGN KEY (item_drop_name) REFERENCES items(item_name)
);
create table equipment (
    equipment_id TINYINT NOT NULL,
    equipped_staff_id TINYINT NOT NULL,
    equipped_robe_id TINYINT NOT NULL,
    equipped_ring_id TINYINT NOT NULL,
    PRIMARY KEY (equipment_id),
    FOREIGN KEY (equipped_staff_id) REFERENCES staffs(staff_id),
    FOREIGN KEY (equipped_robe_id) REFERENCES robes(robe_id),
    FOREIGN KEY (equipped_ring_id) REFERENCES rings(ring_id)
);
create table inventory (
    inventory_id TINYINT NOT NULL,
    item_name VARCHAR(30) NOT NULL,
    item_quantity TINYINT,
    item_type VARCHAR(10),
    PRIMARY KEY (inventory_id),
    FOREIGN KEY (item_name) REFERENCES items(item_name)
);

CREATE TABLE attack_abilities (
    attack_ability_id TINYINT NOT NULL,
    ability_name VARCHAR(30),
    mana_cost TINYINT,
    level_req TINYINT,
    damage SMALLINT,
    agil_damage TINYINT,
    PRIMARY KEY (attack_ability_id)
);

CREATE TABLE defense_abilities (
    defense_ability_id TINYINT NOT NULL,
    ability_name VARCHAR(30),
    mana_cost TINYINT,
    level_req TINYINT,
    defense TINYINT,
    heal_amt SMALLINT,
    PRIMARY KEY (defense_ability_id)
);

CREATE TABLE enemies(
    name VARCHAR(50) NOT NULL,
    stat_id TINYINT NOT NULL,
    attack_ability_id TINYINT NOT NULL,
    defense_ability_id TINYINT NOT NULL,
    image_id TINYINT,
    PRIMARY KEY (name),
    FOREIGN KEY (stat_id) REFERENCES stats(stat_id),
    FOREIGN KEY (attack_ability_id) REFERENCES attack_abilities (attack_ability_id),
    FOREIGN KEY (defense_ability_id) REFERENCES defense_abilities (defense_ability_id)

);

INSERT INTO staffs (staff_id, staff_name, staff_attack, staff_agility)
VALUES (0, "Basic Staff", 1, 1);

INSERT INTO staffs (staff_id, staff_name, staff_attack, staff_agility)
VALUES (1, "Slime Staff", 3, 1);

INSERT INTO staffs (staff_id, staff_name, staff_attack, staff_agility)
VALUES (2, "Seashell Staff", 8, 2);

INSERT INTO staffs (staff_id, staff_name, staff_attack, staff_agility)
VALUES (3, "Hulk Staff", 20, 4);


INSERT INTO robes (robe_id, robe_name, robe_defense, robe_hp)
VALUES (0, "Basic Robe", 1, 10);

INSERT INTO robes (robe_id, robe_name, robe_defense, robe_hp)
VALUES (1, "Slime Robe", 2, 25);

INSERT INTO robes (robe_id, robe_name, robe_defense, robe_hp)
VALUES (2, "Seashell Robe",6, 50);

INSERT INTO robes (robe_id, robe_name, robe_defense, robe_hp)
VALUES (3, "Hulk Robe",12, 100);


INSERT INTO rings (ring_id, ring_name, ring_mane, ring_hp)
VALUES (0, "Basic Ring", 5, 5);

INSERT INTO rings (ring_id, ring_name, ring_mane, ring_hp)
VALUES (1, "Slime Ring", 10, 25);

INSERT INTO rings (ring_id, ring_name, ring_mane, ring_hp)
VALUES (2, "Seashell Ring", 25, 50);

INSERT INTO rings (ring_id, ring_name, ring_mane, ring_hp)
VALUES (3, "Hulk Ring", 50, 100);


INSERT INTO attack_abilities (attack_ability_id, ability_name, mana_cost,
                             level_req, damage, agil_damage)
VALUES (0, "Basic Attack", 5, 0, 10, 0);

INSERT INTO attack_abilities (attack_ability_id, ability_name, mana_cost,
             level_req, damage, agil_damage)
VALUES(1, "Intermediate Attack", 15, 5, 20, 0);

INSERT INTO attack_abilities (attack_ability_id, ability_name, mana_cost,
             level_req, damage, agil_damage)
VALUES(2, "Advanced Attack", 25, 8, 35, 0);


INSERT INTO defense_abilities (defense_ability_id, ability_name, mana_cost, level_req,
                                 defense, heal_amt)
VALUES (0, "Basic Heal", 20, 0, 0, 25);


INSERT INTO items (item_name, craftable_staff_id, craftable_robe_id, craftable_ring_id)
VALUES ("NULL ITEM", 0, 0, 0);

INSERT INTO items (item_name, craftable_staff_id, craftable_robe_id, craftable_ring_id)
VALUES ("Slime", 1, 1, 1);

INSERT INTO items (item_name, craftable_staff_id, craftable_robe_id, craftable_ring_id)
VALUES ("Seashell", 2, 2, 2);

INSERT INTO items (item_name, craftable_staff_id, craftable_robe_id, craftable_ring_id)
VALUES ("Hulk Tissue", 3, 3, 3);


INSERT INTO stats (stat_id, level, max_hp, agility, attack, defense,
                    xp_worth, item_drop_name)
VALUES (0, 1, 100, 1, 1, 1, 0, "NULL ITEM");

INSERT INTO stats (stat_id, level, max_hp, max_mana, agility, attack, defense, xp_worth, item_drop_name)
VALUES (2, 1, 35, 1000, 0, 3, 3, 75, "Seashell");

INSERT INTO stats (stat_id, level, max_hp, max_mana, agility, attack, defense, xp_worth, item_drop_name)
VALUES (3, 1, 75, 1000, 0, 6, 6, 125, "Hulk Tissue");

INSERT INTO stats (stat_id, level, max_hp, max_mana, agility, attack, defense, xp_worth, item_drop_name)
VALUES (1, 1, 15, 1000, 0, 1, 1, 25, "Slime");


INSERT INTO enemies (name, stat_id, attack_ability_id, defense_ability_id, image_id)
VALUES ("Slime",1,0,0,1);

INSERT INTO enemies(name, stat_id, attack_ability_id, defense_ability_id, image_id)
VALUES ("Crab", 2, 1, 0, 2);

INSERT INTO enemies(name, stat_id, attack_ability_id, defense_ability_id, image_id)
VALUES ("Hulk", 3, 2, 0, 3);


INSERT INTO equipment (equipment_id, equipped_staff_id, equipped_robe_id, equipped_ring_id)
VALUES (0, 0, 0, 0);