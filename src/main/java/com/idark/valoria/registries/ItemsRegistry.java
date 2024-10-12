package com.idark.valoria.registries;

import com.idark.valoria.*;
import com.idark.valoria.client.particle.*;
import com.idark.valoria.client.ui.screen.book.unlockable.*;
import com.idark.valoria.core.enums.*;
import com.idark.valoria.core.network.*;
import com.idark.valoria.core.network.packets.particle.*;
import com.idark.valoria.registries.entity.living.decoration.*;
import com.idark.valoria.registries.item.skins.*;
import com.idark.valoria.registries.item.tiers.*;
import com.idark.valoria.registries.item.types.*;
import com.idark.valoria.registries.item.types.curio.*;
import com.idark.valoria.registries.item.types.curio.charm.*;
import com.idark.valoria.registries.item.types.curio.necklace.*;
import com.idark.valoria.registries.item.types.food.*;
import com.idark.valoria.registries.item.types.ranged.*;
import com.idark.valoria.registries.item.types.ranged.bows.*;
import com.idark.valoria.util.*;
import mod.maxbogomol.fluffy_fur.client.particle.data.*;
import net.minecraft.*;
import net.minecraft.core.particles.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.food.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.ArmorItem.*;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.minecraftforge.common.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;
import org.jetbrains.annotations.*;
import org.joml.*;

import java.awt.*;
import java.lang.Math;
import java.util.List;
import java.util.function.*;

public class ItemsRegistry {
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Valoria.ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Valoria.ID);
    public static RegistryObject<Item>
    // Block items
    shadewoodBoat, shadewoodChestBoat, shadewoodSign, shadewoodHangingSign,
    eldritchBoat, eldritchChestBoat, eldritchSign, eldritchHangingSign,

    // armor
    cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots,
    samuraiKabuto, samuraiChestplate, samuraiLeggings, samuraiBoots,
    natureHelmet, natureChestplate, natureLeggings, natureBoots,
    depthHelmet, depthChestplate, depthLeggings, depthBoots,
    infernalHelmet, infernalChestplate, infernalLeggings, infernalBoots,
    awakenedVoidHelmet, awakenedVoidChestplate, awakenedVoidLeggings, awakenedVoidBoots,
    phantasmHelmet, phantasmChestplate, phantasmLeggings, phantasmBoots,

    // materials
    rawCobalt, amberGem, amethystGem, rubyGem, sapphireGem,
    wickedAmethyst, soulShard, unchargedShard,
    spiderFang, natureGift, oceanicShell, infernalStone,
    boneFragment, painCrystal, nihilityShard, illusionStone,
    natureCore, aquariusCore, infernalCore, voidCore,
    natureUpgrade, aquariusUpgrade, infernalUpgrade, voidUpgrade,
    gaibRoot, karusakanRoot, shadeBlossomLeaf, aloePiece,
    dunestoneBrick, tombstoneBrick, ambaneStoneBrick, limestoneBrick, crystalStoneBrick, voidStoneBrick,
    bronzeIngot, pearliumIngot, cobaltIngot, blackGold, ancientIngot,
    natureIngot, aquariusIngot, infernalIngot, voidIngot,
    pyratite,

    // loot bags
    minersBag, gemBag, dirtGeode, stoneGeode,

    // misc
    debugItem, summonBook, soulCollectorEmpty, soulCollector,
    lexicon, cryptPage, voidKey, spectralBladeThrown,
    pick, arcaneTrim,
    // weapons
    club, bronzeSword, spectralBlade, corpseCleaver,
    samuraiKunai, samuraiPoisonedKunai, samuraiKatana, samuraiLongBow,
    silkenBlade, silkenKunai, silkenWakizashi,
    quantumReaper, bloodHound,
    blazeReap, gunpowderCharge, pyratiteCharge,
    ironKatana, goldenKatana, diamondKatana, netheriteKatana, murasama,
    ironScythe, goldenScythe, diamondScythe, netheriteScythe, beast,
    woodenSpear, stoneSpear, ironSpear, goldenSpear, diamondSpear, netheriteSpear, pyratiteSpear, glaive,
    woodenRapier, stoneRapier, ironRapier, goldenRapier, diamondRapier, netheriteRapier,
    throwableBomb, dynamite,

    // tools
    pearliumSword, pearliumPickaxe, pearliumAxe,
    cobaltSword, cobaltPickaxe, cobaltAxe, cobaltShovel, cobaltHoe,
    ent, natureScythe, naturePickaxe, natureAxe, natureShovel, natureHoe, natureBow,
    coralReef, aquariusScythe, aquariusPickaxe, aquariusAxe, aquariusShovel, aquariusHoe, aquariusBow,
    infernalSword, infernalScythe, infernalPickaxe, infernalAxe, infernalShovel, infernalHoe, infernalBow,
    voidEdge, voidScythe, voidPickaxe, voidAxe, voidShovel, voidHoe, voidBow,
    phantom, phantasmBow, eternity,
    wickedArrow, soulArrow,

    // event
    holidayCandy, holidayKatana, holidayPickaxe, holidayAxe,
    candyCorn, pumpkinBomb, wraithKatana, reaperScythe, dreadAxe, soulReaver,

    // accessories
    ironChain, ironNecklaceAmber, ironNecklaceDiamond, ironNecklaceEmerald, ironNecklaceRuby, ironNecklaceSapphire, ironNecklaceHealth, ironNecklaceArmor, ironNecklaceWealth,
    goldenChain, goldenNecklaceAmber, goldenNecklaceDiamond, goldenNecklaceEmerald, goldenNecklaceRuby, goldenNecklaceSapphire, goldenNecklaceHealth, goldenNecklaceArmor, goldenNecklaceWealth,
    netheriteChain, netheriteNecklaceAmber, netheriteNecklaceDiamond, netheriteNecklaceEmerald, netheriteNecklaceRuby, netheriteNecklaceSapphire, netheriteNecklaceHealth, netheriteNecklaceArmor, netheriteNecklaceWealth,
    leatherBelt,
    ironRing, ironRingAmber, ironRingDiamond, ironRingRuby, ironRingEmerald, ironRingSapphire,
    goldenRing, goldenRingAmber, goldenRingDiamond, goldenRingRuby, goldenRingEmerald, goldenRingSapphire,
    netheriteRing, netheriteRingAmber, netheriteRingDiamond, netheriteRingRuby, netheriteRingEmerald, netheriteRingSapphire,
    leatherGloves, ironGloves, goldenGloves, diamondGloves, netheriteGloves,
    amberTotem, amberWinglet, amberGazer,
    emeraldTotem, emeraldWinglet, emeraldGazer,
    amethystTotem, amethystWinglet, amethystGazer,
    rubyTotem, rubyWinglet, rubyGazer,
    brokenMonocle, monocle, jewelryBag,
    pickNecklace,
    // runes
    rune, runeVision, runeWealth, runeCurses, runeStrength, runeAccuracy, runeDeep, runePyro, runeCold,
    // medicine
    aloeBandage, aloeBandageUpgraded, shadeBlossomBandage,
    // food
    applePie, eyeChunk, taintedBerries, cookedGlowVioletSprout,
    cookedAbyssalGlowfern, goblinMeat, cookedGoblinMeat,
    cup, cacaoCup, coffeeCup, teaCup, greenTeaCup,
    woodenCup, beerCup, rumCup,
    bottle, kvassBottle, wineBottle, akvavitBottle, sakeBottle,
    liquorBottle, rumBottle, meadBottle, cognacBottle,
    whiskeyBottle, cokeBottle, toxinsBottle,
    // spawn eggs
    pumpkinContract, goblin, draugr,
    swampWanderer, necromancer, undead,
    shadewoodSpider, succubus, troll,
    mannequin;

    public static void load(IEventBus eventBus){
        shadewoodBoat = BLOCK_ITEMS.register("shadewood_boat", () -> new CustomBoatItem(false, CustomBoatEntity.Type.SHADEWOOD, new Item.Properties().stacksTo(1)));
        shadewoodChestBoat = BLOCK_ITEMS.register("shadewood_chest_boat", () -> new CustomBoatItem(true, CustomBoatEntity.Type.SHADEWOOD, new Item.Properties().stacksTo(1)));
        shadewoodSign = BLOCK_ITEMS.register("shadewood_sign", () -> new SignItem(new Item.Properties().stacksTo(16), BlockRegistry.SHADEWOOD_SIGN.get(), BlockRegistry.SHADEWOOD_WALL_SIGN.get()));
        shadewoodHangingSign = BLOCK_ITEMS.register("shadewood_hanging_sign", () -> new HangingSignItem(BlockRegistry.SHADEWOOD_HANGING_SIGN.get(), BlockRegistry.SHADEWOOD_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
        eldritchBoat = BLOCK_ITEMS.register("eldritch_boat", () -> new CustomBoatItem(false, CustomBoatEntity.Type.ELDRITCH, new Properties().stacksTo(1)));
        eldritchChestBoat = BLOCK_ITEMS.register("eldritch_chest_boat", () -> new CustomBoatItem(true, CustomBoatEntity.Type.ELDRITCH, new Item.Properties().stacksTo(1)));
        eldritchSign = BLOCK_ITEMS.register("eldritch_sign", () -> new SignItem(new Item.Properties().stacksTo(16), BlockRegistry.ELDRITCH_SIGN.get(), BlockRegistry.ELDRITCH_WALL_SIGN.get()));
        eldritchHangingSign = BLOCK_ITEMS.register("eldritch_hanging_sign", () -> new HangingSignItem(BlockRegistry.ELDRITCH_HANGING_SIGN.get(), BlockRegistry.ELDRITCH_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

        cobaltHelmet = registerItem("cobalt_helmet", () -> new SkinableArmorItem(ModArmorMaterial.COBALT, ArmorItem.Type.HELMET, new Item.Properties()));
        cobaltChestplate = registerItem("cobalt_chestplate", () -> new SkinableArmorItem(ModArmorMaterial.COBALT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        cobaltLeggings = registerItem("cobalt_leggings", () -> new SkinableArmorItem(ModArmorMaterial.COBALT, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        cobaltBoots = registerItem("cobalt_boots", () -> new SkinableArmorItem(ModArmorMaterial.COBALT, ArmorItem.Type.BOOTS, new Item.Properties()));
        samuraiKabuto = registerItem("samurai_kabuto", () -> new SamuraiArmorItem(ModArmorMaterial.SAMURAI, ArmorItem.Type.HELMET, new Item.Properties()));
        samuraiChestplate = registerItem("samurai_chestplate", () -> new SamuraiArmorItem(ModArmorMaterial.SAMURAI, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        samuraiLeggings = registerItem("samurai_leggings", () -> new SamuraiArmorItem(ModArmorMaterial.SAMURAI, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        samuraiBoots = registerItem("samurai_boots", () -> new SamuraiArmorItem(ModArmorMaterial.SAMURAI, ArmorItem.Type.BOOTS, new Item.Properties()));

        // elemental
        natureHelmet = registerEffectArmor("nature_helmet", Type.HELMET, ModArmorMaterial.NATURE, new Item.Properties().rarity(RarityRegistry.NATURE));
        natureChestplate = registerEffectArmor("nature_chestplate", Type.CHESTPLATE, ModArmorMaterial.NATURE, new Item.Properties().rarity(RarityRegistry.NATURE));
        natureLeggings = registerEffectArmor("nature_leggings", Type.LEGGINGS, ModArmorMaterial.NATURE, new Item.Properties().rarity(RarityRegistry.NATURE));
        natureBoots = registerEffectArmor("nature_boots", Type.BOOTS, ModArmorMaterial.NATURE, new Item.Properties().rarity(RarityRegistry.NATURE));
        depthHelmet = registerEffectArmor("depth_helmet", Type.HELMET, ModArmorMaterial.DEPTH, new Item.Properties().rarity(RarityRegistry.AQUARIUS));
        depthChestplate = registerEffectArmor("depth_chestplate", Type.CHESTPLATE, ModArmorMaterial.DEPTH, new Item.Properties().rarity(RarityRegistry.AQUARIUS));
        depthLeggings = registerEffectArmor("depth_leggings", Type.LEGGINGS, ModArmorMaterial.DEPTH, new Item.Properties().rarity(RarityRegistry.AQUARIUS));
        depthBoots = registerEffectArmor("depth_boots", Type.BOOTS, ModArmorMaterial.DEPTH, new Item.Properties().rarity(RarityRegistry.AQUARIUS));
        infernalHelmet = registerEffectArmor("infernal_helmet", Type.HELMET, ModArmorMaterial.INFERNAL, new Item.Properties().rarity(RarityRegistry.INFERNAL).fireResistant());
        infernalChestplate = registerEffectArmor("infernal_chestplate", Type.CHESTPLATE, ModArmorMaterial.INFERNAL, new Item.Properties().rarity(RarityRegistry.INFERNAL).fireResistant());
        infernalLeggings = registerEffectArmor("infernal_leggings", Type.LEGGINGS, ModArmorMaterial.INFERNAL, new Item.Properties().rarity(RarityRegistry.INFERNAL).fireResistant());
        infernalBoots = registerEffectArmor("infernal_boots", Type.BOOTS, ModArmorMaterial.INFERNAL, new Item.Properties().rarity(RarityRegistry.INFERNAL).fireResistant());
        awakenedVoidHelmet = registerEffectArmor("awakened_void_helmet", Type.HELMET, ModArmorMaterial.AWAKENED_VOID, new Item.Properties().rarity(RarityRegistry.VOID).fireResistant());
        awakenedVoidChestplate = registerEffectArmor("awakened_void_chestplate", Type.CHESTPLATE, ModArmorMaterial.AWAKENED_VOID, new Item.Properties().rarity(RarityRegistry.VOID).fireResistant());
        awakenedVoidLeggings = registerEffectArmor("awakened_void_leggings", Type.LEGGINGS, ModArmorMaterial.AWAKENED_VOID, new Item.Properties().rarity(RarityRegistry.VOID).fireResistant());
        awakenedVoidBoots = registerEffectArmor("awakened_void_boots", Type.BOOTS, ModArmorMaterial.AWAKENED_VOID, new Item.Properties().rarity(RarityRegistry.VOID).fireResistant());
        phantasmHelmet = registerEffectArmor("phantasm_helmet", Type.HELMET, ModArmorMaterial.PHANTASM, new Item.Properties().rarity(RarityRegistry.PHANTASM).fireResistant());
        phantasmChestplate = registerEffectArmor("phantasm_chestplate", Type.CHESTPLATE, ModArmorMaterial.PHANTASM, new Item.Properties().rarity(RarityRegistry.PHANTASM).fireResistant());
        phantasmLeggings = registerEffectArmor("phantasm_leggings", Type.LEGGINGS, ModArmorMaterial.PHANTASM, new Item.Properties().rarity(RarityRegistry.PHANTASM).fireResistant());
        phantasmBoots = registerEffectArmor("phantasm_boots", Type.BOOTS, ModArmorMaterial.PHANTASM, new Item.Properties().rarity(RarityRegistry.PHANTASM).fireResistant());

        //materials
        rawCobalt = registerItem("raw_cobalt");
        amberGem = registerItem("amber_gem");
        amethystGem = registerItem("amethyst_gem");
        rubyGem = registerItem("ruby_gem");
        sapphireGem = registerItem("sapphire_gem");
        wickedAmethyst = registerItem("wicked_amethyst", () -> new TransformShardItem(new Item.Properties().rarity(RarityRegistry.VOID)));
        soulShard = registerItem("soul_shard", () -> new TransformShardItem(new Item.Properties().rarity(RarityRegistry.AQUARIUS)));
        unchargedShard = registerItem("uncharged_shard");
        spiderFang = registerItem("spider_fang");
        gaibRoot = registerItem("gaib_root", () -> new Item(new Item.Properties().stacksTo(16)));
        karusakanRoot = registerItem("karusakan_root", () -> new Item(new Item.Properties().stacksTo(16)));
        aloePiece = registerItem("aloe_piece");
        shadeBlossomLeaf = registerItem("shade_blossom_leaf");
        dunestoneBrick = registerItem("dunestone_brick");
        tombstoneBrick = registerItem("tombstone_brick");
        ambaneStoneBrick = registerItem("ambane_stone_brick");
        limestoneBrick = registerItem("limestone_brick");
        crystalStoneBrick = registerItem("crystal_stone_brick");
        voidStoneBrick = registerItem("void_stone_brick");
        bronzeIngot = registerItem("bronze_ingot");
        pearliumIngot = registerItem("pearlium_ingot");
        cobaltIngot = registerItem("cobalt_ingot");
        blackGold = registerItem("black_gold_ingot");
        ancientIngot = registerItem("ancient_ingot");
        natureIngot = registerItem("nature_ingot", () -> new Item(new Item.Properties().rarity(RarityRegistry.NATURE)));
        aquariusIngot = registerItem("aquarius_ingot", () -> new Item(new Item.Properties().rarity(RarityRegistry.AQUARIUS)));
        infernalIngot = registerItem("infernal_ingot", () -> new Item(new Item.Properties().fireResistant().rarity(RarityRegistry.INFERNAL)));
        voidIngot = registerItem("void_ingot", () -> new Item(new Item.Properties().fireResistant().rarity(RarityRegistry.VOID)));
        pyratite = registerItem("pyratite", () -> new Item(new Item.Properties().rarity(RarityRegistry.INFERNAL)));
        natureGift = registerItem("nature_gift", () -> new ParticleMaterialItem(new Item.Properties().stacksTo(16).rarity(RarityRegistry.NATURE)){{
            particle = ParticleRegistry.SPHERE.get();
            alpha = 0.35f;
            color = ColorParticleData.create(Pal.nature, Pal.vividCyan).build();
        }});

        boneFragment = registerItem("bone_fragment", () -> new ParticleMaterialItem(new Item.Properties().stacksTo(16).rarity(RarityRegistry.NATURE)){{
            particle = ParticleRegistry.SPHERE.get();
            alpha = 0.35f;
            color = ColorParticleData.create(Pal.vividGreen, Pal.cyan).build();
        }});

        oceanicShell = registerItem("oceanic_shell", () -> new ParticleMaterialItem(new Item.Properties().stacksTo(16).rarity(RarityRegistry.AQUARIUS)){{
            particle = ParticleRegistry.SPHERE.get();
            alpha = 0.35f;
            color = ColorParticleData.create(Pal.oceanic, Pal.magmatic).build();
        }});

        infernalStone = registerItem("infernal_stone", () -> new ParticleMaterialItem(new Item.Properties().stacksTo(16).rarity(RarityRegistry.INFERNAL)){{
            particle = ParticleRegistry.SPHERE.get();
            alpha = 0.35f;
            color = ColorParticleData.create(Pal.infernalBright, Pal.magmatic).build();
        }});

        painCrystal = registerItem("pain_crystal", () -> new ParticleMaterialItem(new Item.Properties().stacksTo(16).rarity(RarityRegistry.NATURE)){{
            particle = ParticleRegistry.SPHERE.get();
            alpha = 0.35f;
            color = ColorParticleData.create(Pal.strongRed, Pal.moderateViolet).build();
        }});

        nihilityShard = registerItem("nihility_shard", () -> new ParticleMaterialItem(new Item.Properties().stacksTo(16).rarity(RarityRegistry.VOID)){{
            particle = ParticleRegistry.SPHERE.get();
            alpha = 0.35f;
            color = ColorParticleData.create(Pal.softMagenta).build();
        }});

        illusionStone = registerItem("illusion_stone", () -> new ParticleMaterialItem(new Item.Properties().stacksTo(16).rarity(RarityRegistry.PHANTASM)){{
            particle = ParticleRegistry.SPHERE.get();
            alpha = 0.35f;
            color = ColorParticleData.create(Pal.softBlue, Color.white).build();
        }});

        natureCore = registerItem("nature_core", () -> new CoreItem(ParticleRegistry.SPHERE.get(), new Item.Properties().fireResistant().rarity(RarityRegistry.NATURE), 8, Pal.nature, Pal.vividCyan, "nature_core"));
        aquariusCore = registerItem("aquarius_core", () -> new CoreItem(ParticleRegistry.SPHERE.get(), new Item.Properties().fireResistant().rarity(RarityRegistry.AQUARIUS), 8, Pal.oceanic, Pal.magmatic, "aquarius_core"));
        infernalCore = registerItem("infernal_core", () -> new CoreItem(ParticleRegistry.SPHERE.get(), new Item.Properties().fireResistant().rarity(RarityRegistry.INFERNAL), 8, Pal.infernalBright, Pal.magmatic, "infernal_core"));
        voidCore = registerItem("void_core", () -> new CoreItem(ParticleRegistry.SPHERE.get(), new Item.Properties().fireResistant().rarity(RarityRegistry.VOID), 8, Pal.softMagenta, Pal.softMagenta, "void_core"));

        natureUpgrade = registerItem("nature_upgrade_smithing_template", () -> ElementalSmithingTemplateItem.createUpgradeTemplate(natureIngot));
        aquariusUpgrade = registerItem("aquarius_upgrade_smithing_template", () -> ElementalSmithingTemplateItem.createUpgradeTemplate(aquariusIngot));
        infernalUpgrade = registerItem("infernal_upgrade_smithing_template", () -> ElementalSmithingTemplateItem.createUpgradeTemplate(infernalIngot));
        voidUpgrade = registerItem("void_upgrade_smithing_template", () -> ElementalSmithingTemplateItem.createUpgradeTemplate(voidIngot));

        // loot bags
        dirtGeode = registerItem("dirt_geode", () -> new Item(new Item.Properties().rarity(Rarity.RARE)) {
            @Override
            public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flags) {
                super.appendHoverText(stack, world, tooltip, flags);
                tooltip.add(Component.translatable("tooltip.valoria.geode").withStyle(ChatFormatting.GRAY));
            }
        });

        stoneGeode = registerItem("stone_geode", () -> new Item(new Item.Properties().rarity(Rarity.RARE)) {
            @Override
            public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flags) {
                super.appendHoverText(stack, world, tooltip, flags);
                tooltip.add(Component.translatable("tooltip.valoria.geode").withStyle(ChatFormatting.GRAY));
            }
        });

        minersBag = registerItem("miners_bag", () -> new DropItemProperty(DropType.MINERS, new Item.Properties().rarity(Rarity.EPIC)));
        gemBag = registerItem("gem_bag", () -> new DropItemProperty(DropType.GEM, new Item.Properties().rarity(Rarity.EPIC)));

        // misc
        debugItem = registerItem("debug_item", () -> new DebugItem(new Item.Properties()));
        summonBook = registerItem("summon_book", () -> new SummonBook(EntityTypeRegistry.UNDEAD, 30, 3, new Item.Properties().rarity(Rarity.EPIC)));
        soulCollectorEmpty = registerItem("soul_collector_empty", () -> new SoulCollectorItem(new Item.Properties().stacksTo(1).rarity(RarityRegistry.PHANTASM)));
        soulCollector = registerItem("soul_collector", () -> new SoulCollectorItem(50, 50, new Item.Properties().rarity(RarityRegistry.PHANTASM)));
        lexicon = registerItem("lexicon", () -> new LexiconItem(new Item.Properties().stacksTo(1)));
        cryptPage = registerItem("page", () -> new LexiconPageItem(new Item.Properties().stacksTo(1), RegisterUnlockables.CRYPT, "gui.valoria.crypt.name"));
        voidKey = registerItem("void_key", () -> new Item(new Item.Properties().stacksTo(16).rarity(RarityRegistry.VOID)));
        pick = registerItem("prospectors_pick", () -> new PickItem(new Item.Properties().fireResistant().stacksTo(1).durability(64), 1, -2.8f, 5));
        arcaneTrim = registerItem("arcane_trim", () -> new SkinTrimItem(SkinsRegistry.ARCANE_GOLD, new Item.Properties()));

        // weapons
        club = registerItem("club", () -> new HitEffectItem(Tiers.WOOD, 5, -3.2f, new Item.Properties(), 0.1f, new MobEffectInstance(EffectsRegistry.STUN.get(), 60, 0)));
        bronzeSword = registerItem("bronze_sword", () -> new SwordItem(Tiers.IRON, 6, -2.4f, new Item.Properties()));
        quantumReaper = registerItem("quantum_reaper", () -> new SwordItem(ModItemTier.NONE, 8, -3f, new Item.Properties().rarity(RarityRegistry.VOID)));
        bloodHound = registerItem("bloodhound", () -> new HoundItem(ModItemTier.BLOOD, 6, -2.2f, new Item.Properties()));
        blazeReap = registerItem("blaze_reap", () -> new BlazeReapItem(ModItemTier.NONE, 3, -3.4f, new Item.Properties()));
        gunpowderCharge = registerItem("gunpowder_charge", () -> new GunpowderCharge(4f, 25f, new Item.Properties().stacksTo(1)));
        pyratiteCharge = registerItem("pyratite_charge", () -> new GunpowderCharge(6f, 40f, new Item.Properties().stacksTo(1)));
        spectralBlade = registerItem("spectral_blade", () -> new SpectralBladeItem(1, -2.3f, new Item.Properties().durability(852)));
        corpseCleaver = registerItem("corpsecleaver", () -> new CorpseCleaverItem(ModItemTier.BLOOD, 2, -2.4F, new Item.Properties().durability(1151)));
        throwableBomb = registerItem("throwable_bomb", () -> new ThrowableBombItem(new Item.Properties().stacksTo(16)));
        dynamite = registerItem("dynamite", () -> new ThrowableBombItem(3f, 60, new Item.Properties().stacksTo(16)));

        // winter
        holidayCandy = registerItem("holiday_candy", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.2f).build())));
        holidayKatana = registerItem("holiday_katana", () -> new KatanaItem(ModItemTier.HOLIDAY, 2, -2.2f, new Item.Properties()));
        holidayPickaxe = registerItem("holiday_pickaxe", () -> new PickaxeItem(ModItemTier.HOLIDAY, -1, -3f, new Item.Properties()));
        holidayAxe = registerItem("holiday_axe", () -> new AxeItem(ModItemTier.HOLIDAY, 1, -3f, new Item.Properties()));

        // halloween
        candyCorn = registerItem("candy_corn", () -> new Item(new Item.Properties().rarity(RarityRegistry.HALLOWEEN).stacksTo(64).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.2f).build())));
        pumpkinBomb = registerItem("pumpkin_bomb", () -> new ThrowableBombItem(new Item.Properties().rarity(RarityRegistry.HALLOWEEN).stacksTo(16)));
        wraithKatana = registerItem("wraith_katana", () -> new KatanaItem.Builder(3, -2.2f, new Item.Properties().rarity(RarityRegistry.HALLOWEEN))
        .setTier(ModItemTier.HALLOWEEN)
        .setDashDistance(1.6f)
        .setSound(SoundsRegistry.HALLOWEEN_SLICE.get())
        .setOverlay(new ResourceLocation(Valoria.ID, "textures/gui/overlay/roots.png"))
        .usePacket(Pal.mandarin)
        .build()
        );

        reaperScythe = registerItem("reaper_scythe", () -> new ScytheItem(ModItemTier.HALLOWEEN, 9, -3.0f, new Item.Properties().fireResistant().rarity(RarityRegistry.HALLOWEEN)){{
            effects = addEffects(0.5f, new MobEffectInstance(MobEffects.DARKNESS, 90, 0));
            abilitySound = SoundsRegistry.HALLOWEEN_SLICE.get();
        }});

        dreadAxe = registerItem("dread_axe", () -> new AxeItem(ModItemTier.HALLOWEEN, 6.5f, -2.8f, new Item.Properties().rarity(RarityRegistry.HALLOWEEN)));
        soulReaver = registerItem("soul_reaver", () -> new HitEffectItem(ModItemTier.HALLOWEEN, 4, -2.8f, new Item.Properties().rarity(RarityRegistry.HALLOWEEN), 0.25f, new MobEffectInstance(MobEffects.DARKNESS, 40, 0), new MobEffectInstance(MobEffects.WEAKNESS, 60, 1)));
        spectralBladeThrown = registerItem("spectral_blade_thrown"); // for rendering
        woodenSpear = registerItem("wooden_spear", () -> new SpearItem(Tiers.WOOD, 3, -3f, 1.4f, new Item.Properties()));
        stoneSpear = registerItem("stone_spear", () -> new SpearItem(Tiers.STONE, 4, -3f, 2, new Item.Properties()));
        ironSpear = registerItem("iron_spear", () -> new SpearItem(Tiers.IRON, 5, -3f, 3.2f, new Item.Properties()));
        goldenSpear = registerItem("golden_spear", () -> new SpearItem(Tiers.GOLD, 5, -2.9f, 3.5f, new Item.Properties()));
        diamondSpear = registerItem("diamond_spear", () -> new SpearItem(Tiers.DIAMOND, 3, -2.9f, 4, new Item.Properties()));
        netheriteSpear = registerItem("netherite_spear", () -> new SpearItem(Tiers.NETHERITE, 3, -2.9f, 6, new Item.Properties()));
        pyratiteSpear = registerItem("pyratite_spear", () -> new ExplosiveSpearItem(ModItemTier.PYRATITE, 5, -2.9f, 7.5f, 0.75f, Level.ExplosionInteraction.NONE, new Item.Properties().rarity(RarityRegistry.INFERNAL)));
        glaive = registerItem("glaive", () -> new SpearItem(Tiers.IRON, 8, -3.2f, false, new Item.Properties()));
        woodenRapier = registerItem("wooden_rapier", () -> new SwordItem(Tiers.WOOD, 0, -1.8f, new Item.Properties()));
        stoneRapier = registerItem("stone_rapier", () -> new SwordItem(Tiers.STONE, 0, -1.8f, new Item.Properties()));
        ironRapier = registerItem("iron_rapier", () -> new SwordItem(Tiers.IRON, 1, -1.7f, new Item.Properties()));
        goldenRapier = registerItem("golden_rapier", () -> new SwordItem(Tiers.GOLD, 0, -1.5f, new Item.Properties()));
        diamondRapier = registerItem("diamond_rapier", () -> new SwordItem(Tiers.DIAMOND, 2, -1.5f, new Item.Properties()));
        netheriteRapier = registerItem("netherite_rapier", () -> new SwordItem(Tiers.NETHERITE, 2, -1.5f, new Item.Properties()));
        ironScythe = registerItem("iron_scythe", () -> new ScytheItem(Tiers.IRON, 5, -3.2f, new Item.Properties()));
        goldenScythe = registerItem("golden_scythe", () -> new ScytheItem(Tiers.GOLD, 5, -3.1f, new Item.Properties()));
        diamondScythe = registerItem("diamond_scythe", () -> new ScytheItem(Tiers.DIAMOND, 8, -3.0f, new Item.Properties()));
        netheriteScythe = registerItem("netherite_scythe", () -> new ScytheItem(Tiers.NETHERITE, 10, -3.0f, new Item.Properties().fireResistant()));
        beast = registerItem("beast", () -> new BeastScytheItem(ModItemTier.NONE, 13, -3.2f, new Item.Properties()) {{
            minCooldown = 40;
            maxCooldown = 150;
        }
        });

        ironKatana = registerItem("iron_katana", () -> new KatanaItem(Tiers.IRON, 3, -2.2f, 1f, new Item.Properties()));
        goldenKatana = registerItem("golden_katana", () -> new KatanaItem(Tiers.GOLD, 2, -1.8f, 1.2f, new Item.Properties()));
        diamondKatana = registerItem("diamond_katana", () -> new KatanaItem(Tiers.DIAMOND, 4, -2f, 1.3f, new Item.Properties()));
        netheriteKatana = registerItem("netherite_katana", () -> new KatanaItem(Tiers.NETHERITE, 5, -1.8f, 1.5f, new Item.Properties().fireResistant()));
        murasama = registerItem("murasama", ItemsRegistry::murasamaProps);
        samuraiKunai = registerItem("samurai_kunai", () -> new KunaiItem(3, -1.9F, new Item.Properties().durability(360)));
        samuraiPoisonedKunai = registerItem("samurai_poisoned_kunai", () -> new KunaiItem(3, -1.9F, new Item.Properties().durability(360), new MobEffectInstance(MobEffects.POISON, 170, 0)));
        samuraiKatana = registerItem("samurai_katana", () -> new KatanaItem(ModItemTier.SAMURAI, 7, -2f, new Item.Properties()));
        samuraiLongBow = registerItem("samurai_long_bow", () -> new ConfigurableBowItem(3, new Item.Properties().stacksTo(1).durability(684)));
        silkenBlade = registerItem("silken_blade", () -> new HitEffectItem(ModItemTier.NONE, 5, -3.2f, new Item.Properties(), 0.25f, new MobEffectInstance(MobEffects.DARKNESS, 120, 0), new MobEffectInstance(MobEffects.POISON, 45, 0)));
        silkenWakizashi = registerItem("silken_wakizashi", () -> new KatanaItem(ModItemTier.NONE, 11, -2.2f, 3, new Item.Properties(), 0.25f, new MobEffectInstance(MobEffects.DARKNESS, 120, 0), new MobEffectInstance(MobEffects.POISON, 45, 0)));
        silkenKunai = registerItem("silken_kunai", () -> new KunaiItem(0, -2.2f, new Item.Properties(), 0.25f, new MobEffectInstance(MobEffects.DARKNESS, 120, 0), new MobEffectInstance(MobEffects.POISON, 60, 1)));

        pearliumSword = registerItem("pearlium_sword", () -> new SwordItem(ModItemTier.PEARLIUM, 5, -2.6f, new Item.Properties()));
        pearliumPickaxe = registerItem("pearlium_pickaxe", () -> new PickaxeItem(ModItemTier.PEARLIUM, -1, -3f, new Item.Properties()));
        pearliumAxe = registerItem("pearlium_axe", () -> new AxeItem(ModItemTier.PEARLIUM, 6, -3f, new Item.Properties()));
        cobaltSword = registerItem("cobalt_sword", () -> new SwordItem(ModItemTier.COBALT, 9, -2.2f, new Item.Properties()));
        cobaltPickaxe = registerItem("cobalt_pickaxe", () -> new PickaxeItem(ModItemTier.COBALT, 4, -3f, new Item.Properties()));
        cobaltAxe = registerItem("cobalt_axe", () -> new AxeItem(ModItemTier.COBALT, 10.5f, -2.8f, new Item.Properties()));
        cobaltShovel = registerItem("cobalt_shovel", () -> new ShovelItem(ModItemTier.COBALT, 5.25f, -3f, new Item.Properties()));
        cobaltHoe = registerItem("cobalt_hoe", () -> new HoeItem(ModItemTier.COBALT, 0, 0f, new Item.Properties()));
        ent = registerItem("ent", () -> new SwordItem(ModItemTier.NATURE, 11, -2.4f, new Item.Properties().rarity(RarityRegistry.NATURE)));
        naturePickaxe = registerItem("nature_pickaxe", () -> new PickaxeItem(ModItemTier.NATURE, 5, -3f, new Item.Properties().rarity(RarityRegistry.NATURE)));
        natureScythe = registerItem("nature_scythe", () -> new ScytheItem(ModItemTier.NATURE, 11, -3.0f, new Item.Properties().rarity(RarityRegistry.NATURE)));
        natureAxe = registerItem("nature_axe", () -> new AxeItem(ModItemTier.NATURE, 12f, -2.8f, new Item.Properties().rarity(RarityRegistry.NATURE)));
        natureShovel = registerItem("nature_shovel", () -> new ShovelItem(ModItemTier.NATURE, 6, -3f, new Item.Properties().rarity(RarityRegistry.NATURE)));
        natureHoe = registerItem("nature_hoe", () -> new HoeItem(ModItemTier.NATURE, 0, 0f, new Item.Properties().rarity(RarityRegistry.NATURE)));
        natureBow = registerItem("nature_bow", () -> new ConfigurableBowItem(2, new Item.Properties().stacksTo(1).durability(1024).rarity(RarityRegistry.NATURE)));
        coralReef = registerItem("coral_reef", () -> new CoralReefItem(ModItemTier.AQUARIUS, 12, -2.4f, new Item.Properties().rarity(RarityRegistry.AQUARIUS)));
        aquariusScythe = registerItem("aquarius_scythe", () -> new AquariusScytheItem(ModItemTier.AQUARIUS, 12, -3.0f, new Item.Properties().rarity(RarityRegistry.AQUARIUS)));
        aquariusPickaxe = registerItem("aquarius_pickaxe", () -> new PickaxeItem(ModItemTier.AQUARIUS, 6, -3f, new Item.Properties().rarity(RarityRegistry.AQUARIUS)));
        aquariusAxe = registerItem("aquarius_axe", () -> new AxeItem(ModItemTier.AQUARIUS, 13f, -2.8f, new Item.Properties().rarity(RarityRegistry.AQUARIUS)));
        aquariusShovel = registerItem("aquarius_shovel", () -> new ShovelItem(ModItemTier.AQUARIUS, 6.25f, -3f, new Item.Properties().rarity(RarityRegistry.AQUARIUS)));
        aquariusHoe = registerItem("aquarius_hoe", () -> new HoeItem(ModItemTier.AQUARIUS, 0, 0f, new Item.Properties().rarity(RarityRegistry.AQUARIUS)));
        aquariusBow = registerItem("aquarius_bow", () -> new ConfigurableBowItem(3, new Item.Properties().stacksTo(1).durability(1324).fireResistant().rarity(RarityRegistry.AQUARIUS)));
        infernalSword = registerItem("infernal_sword", () -> new MagmaSwordItem(ModItemTier.INFERNAL, 15, -2.6f, new Item.Properties().fireResistant().rarity(RarityRegistry.INFERNAL)));
        infernalScythe = registerItem("infernal_scythe", () -> new InfernalScytheItem(ModItemTier.INFERNAL, 14, -3.0f, new Item.Properties().fireResistant().rarity(RarityRegistry.INFERNAL)));
        infernalPickaxe = registerItem("infernal_pickaxe", () -> new PickaxeItem(ModItemTier.INFERNAL, 7, -2.8f, new Item.Properties().fireResistant().rarity(RarityRegistry.INFERNAL)));
        infernalAxe = registerItem("infernal_axe", () -> new AxeItem(ModItemTier.INFERNAL, 16.25f, -2.8f, new Item.Properties().fireResistant().rarity(RarityRegistry.INFERNAL)));
        infernalShovel = registerItem("infernal_shovel", () -> new ShovelItem(ModItemTier.INFERNAL, 7.5f, -2.9f, new Item.Properties().fireResistant().rarity(RarityRegistry.INFERNAL)));
        infernalHoe = registerItem("infernal_hoe", () -> new HoeItem(ModItemTier.INFERNAL, 0, 0f, new Item.Properties().fireResistant().rarity(RarityRegistry.INFERNAL)));
        infernalBow = registerItem("infernal_bow", () -> new ConfigurableBowItem(EntityTypeRegistry.INFERNAL_ARROW, 4, 3, new Item.Properties().fireResistant().stacksTo(1).durability(1684).rarity(RarityRegistry.INFERNAL)));
        voidEdge = registerItem("void_edge", () -> new SwordItem(ModItemTier.NIHILITY, 10, -2.55f, new Item.Properties().rarity(RarityRegistry.VOID)));
        voidScythe = registerItem("void_scythe", () -> new ScytheItem(ModItemTier.NIHILITY, 16, -3.0f, new Item.Properties().fireResistant().rarity(RarityRegistry.VOID)){{
            effects = addEffects(0.5f, new MobEffectInstance(MobEffects.DARKNESS, 90, 0));
        }});

        voidPickaxe = registerItem("void_pickaxe", () -> new PickaxeItem(ModItemTier.NIHILITY, 8, -2.8f, new Item.Properties().fireResistant().rarity(RarityRegistry.VOID)));
        voidAxe = registerItem("void_axe", () -> new AxeItem(ModItemTier.NIHILITY, 18f, -2.8f, new Item.Properties().fireResistant().rarity(RarityRegistry.VOID)));
        voidShovel = registerItem("void_shovel", () -> new ShovelItem(ModItemTier.NIHILITY, 8.5f, -2.9f, new Item.Properties().fireResistant().rarity(RarityRegistry.VOID)));
        voidHoe = registerItem("void_hoe", () -> new HoeItem(ModItemTier.NIHILITY, 0, 0f, new Item.Properties().fireResistant().rarity(RarityRegistry.VOID)));
        voidBow = registerItem("bow_of_darkness", () -> new ConfigurableBowItem(4.25f, new Item.Properties().stacksTo(1).durability(2048).fireResistant().rarity(RarityRegistry.VOID)));
        phantom = registerItem("phantom", () -> new PhantomItem(ModItemTier.NONE, 6, -2.4f, new Item.Properties().rarity(RarityRegistry.PHANTASM)));
        phantasmBow = registerItem("phantasm_bow", () -> new ConfigurableBowItem(EntityTypeRegistry.PHANTOM_ARROW, 6, 4, new Item.Properties().fireResistant().stacksTo(1).durability(4028).rarity(RarityRegistry.PHANTASM)));
        wickedArrow = registerItem("wicked_arrow", () -> new WickedArrowItem(new Item.Properties().rarity(RarityRegistry.VOID)));
        soulArrow = registerItem("soul_arrow", () -> new SoulArrowItem(new Item.Properties().rarity(RarityRegistry.AQUARIUS)));
        eternity = registerItem("eternity");

        // accessories
        ironChain = registerItem("iron_chain", () -> new Item(new Item.Properties().stacksTo(8).rarity(Rarity.EPIC)));
        ironNecklaceAmber = registerItem("iron_necklace_amber", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.NECKLACE, AccessoryGem.AMBER, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        ironNecklaceDiamond = registerItem("iron_necklace_diamond", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.NECKLACE, AccessoryGem.DIAMOND, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        ironNecklaceEmerald = registerItem("iron_necklace_emerald", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.NECKLACE, AccessoryGem.EMERALD, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        ironNecklaceRuby = registerItem("iron_necklace_ruby", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.NECKLACE, AccessoryGem.RUBY, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        ironNecklaceSapphire = registerItem("iron_necklace_sapphire", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.NECKLACE, AccessoryGem.SAPPHIRE, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        ironNecklaceHealth = registerItem("iron_necklace_health", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.NECKLACE, AccessoryGem.HEALTH, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        ironNecklaceArmor = registerItem("iron_necklace_armor", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.NECKLACE, AccessoryGem.ARMOR, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        ironNecklaceWealth = registerItem("iron_necklace_wealth", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.NECKLACE, AccessoryGem.WEALTH, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        goldenChain = registerItem("golden_chain", () -> new Item(new Item.Properties().stacksTo(8).rarity(Rarity.UNCOMMON)));
        goldenNecklaceAmber = registerItem("golden_necklace_amber", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.NECKLACE, AccessoryGem.AMBER, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        goldenNecklaceDiamond = registerItem("golden_necklace_diamond", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.NECKLACE, AccessoryGem.DIAMOND, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        goldenNecklaceEmerald = registerItem("golden_necklace_emerald", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.NECKLACE, AccessoryGem.EMERALD, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        goldenNecklaceRuby = registerItem("golden_necklace_ruby", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.NECKLACE, AccessoryGem.RUBY, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        goldenNecklaceSapphire = registerItem("golden_necklace_sapphire", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.NECKLACE, AccessoryGem.SAPPHIRE, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        goldenNecklaceHealth = registerItem("golden_necklace_health", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.NECKLACE, AccessoryGem.HEALTH, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        goldenNecklaceArmor = registerItem("golden_necklace_armor", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.NECKLACE, AccessoryGem.ARMOR, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        goldenNecklaceWealth = registerItem("golden_necklace_wealth", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.NECKLACE, AccessoryGem.WEALTH, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        netheriteChain = registerItem("netherite_chain", () -> new Item(new Item.Properties().stacksTo(8).rarity(Rarity.UNCOMMON)));
        netheriteNecklaceAmber = registerItem("netherite_necklace_amber", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.NECKLACE, AccessoryGem.AMBER, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(500).rarity(Rarity.EPIC)));
        netheriteNecklaceDiamond = registerItem("netherite_necklace_diamond", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.NECKLACE, AccessoryGem.DIAMOND, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(500).rarity(Rarity.EPIC)));
        netheriteNecklaceEmerald = registerItem("netherite_necklace_emerald", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.NECKLACE, AccessoryGem.EMERALD, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(500).rarity(Rarity.EPIC)));
        netheriteNecklaceRuby = registerItem("netherite_necklace_ruby", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.NECKLACE, AccessoryGem.RUBY, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(500).rarity(Rarity.EPIC)));
        netheriteNecklaceSapphire = registerItem("netherite_necklace_sapphire", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.NECKLACE, AccessoryGem.SAPPHIRE, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(500).rarity(Rarity.EPIC)));
        netheriteNecklaceHealth = registerItem("netherite_necklace_health", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.NECKLACE, AccessoryGem.HEALTH, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(500).rarity(Rarity.EPIC)));
        netheriteNecklaceArmor = registerItem("netherite_necklace_armor", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.NECKLACE, AccessoryGem.ARMOR, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(500).rarity(Rarity.EPIC)));
        netheriteNecklaceWealth = registerItem("netherite_necklace_wealth", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.NECKLACE, AccessoryGem.WEALTH, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(500).rarity(Rarity.EPIC)));
        leatherBelt = registerItem("leather_belt", () -> new CurioItemProperty(ModItemTier.NONE, AccessoryType.BELT, AccessoryGem.BELT, AccessoryMaterial.LEATHER, new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)));
        ironRing = registerItem("iron_ring", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.RING, AccessoryGem.NONE, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(80).rarity(Rarity.UNCOMMON)));
        ironRingAmber = registerItem("iron_ring_amber", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.RING, AccessoryGem.AMBER, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        ironRingDiamond = registerItem("iron_ring_diamond", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.RING, AccessoryGem.DIAMOND, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        ironRingEmerald = registerItem("iron_ring_emerald", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.RING, AccessoryGem.EMERALD, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        ironRingRuby = registerItem("iron_ring_ruby", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.RING, AccessoryGem.RUBY, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        ironRingSapphire = registerItem("iron_ring_sapphire", () -> new CurioItemProperty(Tiers.IRON, AccessoryType.RING, AccessoryGem.SAPPHIRE, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        goldenRing = registerItem("golden_ring", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.RING, AccessoryGem.NONE, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(40).rarity(Rarity.UNCOMMON)));
        goldenRingAmber = registerItem("golden_ring_amber", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.RING, AccessoryGem.AMBER, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        goldenRingDiamond = registerItem("golden_ring_diamond", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.RING, AccessoryGem.DIAMOND, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        goldenRingEmerald = registerItem("golden_ring_emerald", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.RING, AccessoryGem.EMERALD, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        goldenRingRuby = registerItem("golden_ring_ruby", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.RING, AccessoryGem.RUBY, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        goldenRingSapphire = registerItem("golden_ring_sapphire", () -> new CurioItemProperty(Tiers.GOLD, AccessoryType.RING, AccessoryGem.SAPPHIRE, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(200).rarity(Rarity.EPIC)));
        netheriteRing = registerItem("netherite_ring", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.RING, AccessoryGem.NONE, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.UNCOMMON)));
        netheriteRingAmber = registerItem("netherite_ring_amber", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.RING, AccessoryGem.AMBER, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(420).rarity(Rarity.EPIC)));
        netheriteRingDiamond = registerItem("netherite_ring_diamond", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.RING, AccessoryGem.DIAMOND, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(420).rarity(Rarity.EPIC)));
        netheriteRingEmerald = registerItem("netherite_ring_emerald", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.RING, AccessoryGem.EMERALD, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(420).rarity(Rarity.EPIC)));
        netheriteRingRuby = registerItem("netherite_ring_ruby", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.RING, AccessoryGem.RUBY, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(420).rarity(Rarity.EPIC)));
        netheriteRingSapphire = registerItem("netherite_ring_sapphire", () -> new CurioItemProperty(Tiers.NETHERITE, AccessoryType.RING, AccessoryGem.SAPPHIRE, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(420).rarity(Rarity.EPIC)));
        leatherGloves = registerItem("leather_gloves", () -> new DyeableGlovesItem(ModItemTier.NONE, new Item.Properties().stacksTo(1).durability(100).rarity(Rarity.UNCOMMON)));
        ironGloves = registerItem("iron_gloves", () -> new GlovesItem(Tiers.IRON, AccessoryGem.ARMOR, AccessoryMaterial.IRON, new Item.Properties().stacksTo(1).durability(190).rarity(Rarity.UNCOMMON)));
        goldenGloves = registerItem("golden_gloves", () -> new GlovesItem(Tiers.GOLD, AccessoryGem.TOUGH, AccessoryMaterial.GOLD, new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.UNCOMMON)));
        diamondGloves = registerItem("diamond_gloves", () -> new GlovesItem(Tiers.DIAMOND, AccessoryGem.DIAMOND, AccessoryMaterial.DIAMOND, new Item.Properties().stacksTo(1).durability(240).rarity(Rarity.UNCOMMON)));
        netheriteGloves = registerItem("netherite_gloves", () -> new GlovesItem(Tiers.NETHERITE, AccessoryGem.TANK, AccessoryMaterial.NETHERITE, new Item.Properties().stacksTo(1).durability(300).rarity(Rarity.UNCOMMON)));
        amberTotem = registerItem("amber_golden_totem", () -> new Item(new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)));
        amberWinglet = registerItem("amber_golden_winglet", () -> new Item(new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)));
        amberGazer = registerItem("amber_golden_gazer", () -> new ParticleMaterialItem(new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)){{
            color = ColorParticleData.create(Pal.amber, Color.white).build();
            alpha = 0.35f;
        }});

        emeraldTotem = registerItem("emerald_golden_totem", () -> new Item(new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)));
        emeraldWinglet = registerItem("emerald_golden_winglet", () -> new Item(new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)));
        emeraldGazer = registerItem("emerald_golden_gazer", () -> new ParticleMaterialItem(new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)){{
            color = ColorParticleData.create(Pal.emerald, Color.white).build();
            alpha = 0.35f;
        }});

        amethystTotem = registerItem("amethyst_golden_totem", () -> new Item(new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)));
        amethystWinglet = registerItem("amethyst_golden_winglet", () -> new Item(new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)));
        amethystGazer = registerItem("amethyst_golden_gazer", () -> new ParticleMaterialItem(new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)){{
            color = ColorParticleData.create(Pal.amethyst, Color.white).build();
            alpha = 0.35f;
        }});

        rubyTotem = registerItem("ruby_golden_totem", () -> new Item(new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)));
        rubyWinglet = registerItem("ruby_golden_winglet", () -> new Item(new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)));
        rubyGazer = registerItem("ruby_golden_gazer", () -> new ParticleMaterialItem(new Item.Properties().stacksTo(1).durability(140).rarity(Rarity.EPIC)){{
            color = ColorParticleData.create(Pal.ruby, Color.white).build();
            alpha = 0.35f;
        }});

        brokenMonocle = registerItem("broken_bloodsight_monocle", () -> new BloodSight(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).durability(300)));
        monocle = registerItem("bloodsight_monocle", () -> new BloodSight(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).durability(300)));
        jewelryBag = registerItem("jewelry_bag", () -> new JewelryBagItem(new Item.Properties().stacksTo(1)));
        pickNecklace = registerItem("pick_necklace", () -> new PickNecklace(new Item.Properties().stacksTo(1).durability(320).rarity(Rarity.EPIC)));
        rune = registerItem("rune", () -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)) {
            @Override
            public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags){
                super.appendHoverText(stack, world, tooltip, flags);
                tooltip.add(Component.translatable("tooltip.valoria.rune").withStyle(ChatFormatting.GRAY));
            }
        });

        runeVision = registerItem("rune_of_vision", () -> new CurioVision(new Item.Properties().stacksTo(1).durability(16).rarity(Rarity.EPIC)));
        runeWealth = registerItem("rune_of_wealth", () -> new CurioWealth(new Item.Properties().stacksTo(1).durability(16).rarity(Rarity.EPIC)));
        runeCurses = registerItem("rune_of_curses", () -> new CurioCurses(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
        runeStrength = registerItem("rune_of_strength", () -> new CurioStrength(new Item.Properties().stacksTo(1).durability(16).rarity(Rarity.EPIC)));
        runeAccuracy = registerItem("rune_of_accuracy", () -> new RuneAccuracy(new Item.Properties().stacksTo(1).durability(16).rarity(Rarity.EPIC)));
        runeDeep = registerItem("rune_of_deep", () -> new RuneDeep(new Item.Properties().stacksTo(1).durability(16).rarity(Rarity.EPIC)));
        runePyro = registerItem("rune_of_pyro", () -> new CurioPyro(new Item.Properties().stacksTo(1).durability(16).rarity(Rarity.EPIC)));
        runeCold = registerItem("rune_of_cold", () -> new RuneCold(new Item.Properties().stacksTo(1).durability(16).rarity(Rarity.EPIC)));

        // medicine
        aloeBandage = registerItem("aloe_bandage", () -> new BandageItem(false, 1600, 0));
        aloeBandageUpgraded = registerItem("aloe_bandage_upgraded", () -> new BandageItem(true, 1450, 1));
        shadeBlossomBandage = registerItem("shade_blossom_bandage", () -> new BandageItem(true, 1750, 1)); //todo custom effect

        //food
        applePie = registerItem("apple_pie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1.4f).build())));
        eyeChunk = registerItem("eye_chunk", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().effect(new MobEffectInstance(MobEffects.POISON, 100), 0.4f).effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300), 1f).nutrition(1).saturationMod(0.2f).fast().build())));
        taintedBerries = registerItem("tainted_berries", () -> new ItemNameBlockItem(BlockRegistry.TAINTED_ROOTS.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3f).fast().build())));
        cookedGlowVioletSprout = registerItem("cooked_glow_violet_sprout", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.5f).effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 500), 1f).build())));
        cookedAbyssalGlowfern = registerItem("cooked_abyssal_glowfern", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.5f).effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 500), 1f).build())));
        goblinMeat = registerItem("goblin_meat", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.2f).build())));
        cookedGoblinMeat = registerItem("cooked_goblin_meat", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.6f).build())));
        cup = registerItem("cup", () -> new BlockItem(BlockRegistry.CUP.get(), new Item.Properties().stacksTo(64)));
        cacaoCup = registerItem("cacao_cup", () -> new PlaceableDrinkItem(BlockRegistry.CACAO_CUP.get(), 0, 1, 64, ItemsRegistry.cup.get(), new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 250)));
        coffeeCup = registerItem("coffee_cup", () -> new PlaceableDrinkItem(BlockRegistry.COFFEE_CUP.get(), 0, 1, 64, ItemsRegistry.cup.get(), new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 250)));
        teaCup = registerItem("tea_cup", () -> new PlaceableDrinkItem(BlockRegistry.TEA_CUP.get(), 0, 1, 16, ItemsRegistry.cup.get(), new MobEffectInstance(MobEffects.DIG_SPEED, 100)));
        greenTeaCup = registerItem("green_tea_cup", () -> new PlaceableDrinkItem(BlockRegistry.GREEN_TEA_CUP.get(), 0, 1, 64, ItemsRegistry.cup.get(), new MobEffectInstance(EffectsRegistry.ALOEREGEN.get(), 1800)));
        woodenCup = registerItem("wooden_cup", () -> new BlockItem(BlockRegistry.WOODEN_CUP.get(), new Item.Properties()));
        beerCup = registerItem("beer_cup", () -> new PlaceableDrinkItem(BlockRegistry.BEER_CUP.get(), 0, 1, 64, ItemsRegistry.woodenCup.get(), new MobEffectInstance(EffectsRegistry.TIPSY.get(), 400, 0)));
        rumCup = registerItem("rum_cup", () -> new PlaceableDrinkItem(BlockRegistry.RUM_CUP.get(), 0, 1, 64, ItemsRegistry.woodenCup.get(), new MobEffectInstance(EffectsRegistry.TIPSY.get(), 400, 0), new MobEffectInstance(MobEffects.CONFUSION, 120, 0)));
        bottle = registerItem("bottle", () -> new BlockItem(BlockRegistry.GLASS_BOTTLE.get(), new Item.Properties().stacksTo(64)));
        kvassBottle = registerItem("kvass_bottle", () -> new PlaceableDrinkItem(BlockRegistry.KVASS_BOTTLE.get(), 0, 1, 64, ItemsRegistry.bottle.get(), new MobEffectInstance(EffectsRegistry.ALOEREGEN.get(), 200)));
        wineBottle = registerItem("wine_bottle", () -> new PlaceableDrinkItem(BlockRegistry.WINE_BOTTLE.get(), 0, 1, 64, ItemsRegistry.bottle.get(), new MobEffectInstance(EffectsRegistry.TIPSY.get(), 450, 1), new MobEffectInstance(MobEffects.CONFUSION, 125)));
        akvavitBottle = registerItem("akvavit_bottle", () -> new PlaceableDrinkItem(BlockRegistry.AKVAVIT_BOTTLE.get(), 0, 1, 64, ItemsRegistry.bottle.get(), new MobEffectInstance(EffectsRegistry.TIPSY.get(), 500, 1), new MobEffectInstance(MobEffects.CONFUSION, 125)));
        sakeBottle = registerItem("sake_bottle", () -> new PlaceableDrinkItem(BlockRegistry.SAKE_BOTTLE.get(), 0, 1, 64, ItemsRegistry.bottle.get(), new MobEffectInstance(EffectsRegistry.TIPSY.get(), 700, 1), new MobEffectInstance(MobEffects.CONFUSION, 175)));
        liquorBottle = registerItem("liquor_bottle", () -> new PlaceableDrinkItem(BlockRegistry.LIQUOR_BOTTLE.get(), 0, 1, 64, ItemsRegistry.bottle.get(), new MobEffectInstance(EffectsRegistry.TIPSY.get(), 350, 1), new MobEffectInstance(MobEffects.CONFUSION, 60)));
        rumBottle = registerItem("rum_bottle", () -> new PlaceableDrinkItem(BlockRegistry.RUM_BOTTLE.get(), 0, 1, 64, ItemsRegistry.bottle.get(), new MobEffectInstance(EffectsRegistry.TIPSY.get(), 650, 1), new MobEffectInstance(MobEffects.CONFUSION, 100)));
        meadBottle = registerItem("mead_bottle", () -> new PlaceableDrinkItem(BlockRegistry.MEAD_BOTTLE.get(), 0, 1, 64, ItemsRegistry.bottle.get(), new MobEffectInstance(EffectsRegistry.TIPSY.get(), 700, 0), new MobEffectInstance(MobEffects.CONFUSION, 100)));
        cognacBottle = registerItem("cognac_bottle", () -> new PlaceableDrinkItem(BlockRegistry.COGNAC_BOTTLE.get(), 0, 1, 64, ItemsRegistry.bottle.get(), new MobEffectInstance(EffectsRegistry.TIPSY.get(), 800, 2), new MobEffectInstance(MobEffects.CONFUSION, 175)));
        whiskeyBottle = registerItem("whiskey_bottle", () -> new PlaceableDrinkItem(BlockRegistry.WHISKEY_BOTTLE.get(), 0, 1, 64, ItemsRegistry.bottle.get(), new MobEffectInstance(EffectsRegistry.TIPSY.get(), 450, 1), new MobEffectInstance(MobEffects.CONFUSION, 125)));
        cokeBottle = registerItem("coke_bottle", () -> new PlaceableDrinkItem(BlockRegistry.COKE_BOTTLE.get(), 0, 1, 64, ItemsRegistry.bottle.get(), new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 250)));
        toxinsBottle = registerItem("toxins_bottle", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));

        // spawn eggs
        pumpkinContract = registerItem("pumpkin_contract", () -> new TexturedSpawnEggItem(EntityTypeRegistry.HAUNTED_MERCHANT, new Item.Properties()));
        mannequin = registerItem("mannequin_spawn_egg", () -> new MannequinSpawnItem(new Item.Properties()));
        goblin = registerItem("goblin_spawn_egg", () -> new ForgeSpawnEggItem(EntityTypeRegistry.GOBLIN, ColorUtil.hexToDecimal("185b36"), ColorUtil.hexToDecimal("6BB447"), new Item.Properties()));
        draugr = registerItem("draugr_spawn_egg", () -> new ForgeSpawnEggItem(EntityTypeRegistry.DRAUGR, ColorUtil.hexToDecimal("76695C"), ColorUtil.hexToDecimal("d6d0c9"), new Item.Properties()));
        swampWanderer = registerItem("swamp_wanderer_spawn_egg", () -> new ForgeSpawnEggItem(EntityTypeRegistry.SWAMP_WANDERER, ColorUtil.hexToDecimal("606239"), ColorUtil.hexToDecimal("b8b377"), new Item.Properties()));
        necromancer = registerItem("necromancer_spawn_egg", () -> new ForgeSpawnEggItem(EntityTypeRegistry.NECROMANCER, ColorUtil.hexToDecimal("4b4857"), ColorUtil.hexToDecimal("958fb7"), new Item.Properties()));
        undead = registerItem("undead_spawn_egg", () -> new ForgeSpawnEggItem(EntityTypeRegistry.UNDEAD, ColorUtil.hexToDecimal("625F71"), ColorUtil.hexToDecimal("ffffff"), new Item.Properties()));
        shadewoodSpider = registerItem("shadewood_spider_spawn_egg", () -> new ForgeSpawnEggItem(EntityTypeRegistry.SHADEWOOD_SPIDER, ColorUtil.hexToDecimal("373C53"), ColorUtil.hexToDecimal("6EABB7"), new Item.Properties()));
        succubus = registerItem("succubus_spawn_egg", () -> new ForgeSpawnEggItem(EntityTypeRegistry.SUCCUBUS, ColorUtil.hexToDecimal("b64841"), ColorUtil.hexToDecimal("3a3b62"), new Item.Properties()));
        troll = registerItem("troll_spawn_egg", () -> new ForgeSpawnEggItem(EntityTypeRegistry.TROLL, ColorUtil.hexToDecimal("2d3a4a"), ColorUtil.hexToDecimal("847461"), new Item.Properties()));

        ITEMS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }

    private static RegistryObject<Item> registerEffectArmor(String name, ArmorItem.Type type, ArmorMaterial material, Item.Properties props) {
        return ITEMS.register(name, () -> new EffectArmorItem(material, type, props));
    }

    private static RegistryObject<Item> registerItem(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    private static RegistryObject<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(name, item);
    }


    private static KatanaItem murasamaProps(){
        return new KatanaItem(ModItemTier.SAMURAI, 9, -2.4f, new Item.Properties()){{
            chargeTime = 20;
            chargedEvent = SoundsRegistry.RECHARGE.get();
        }

            public void onUseTick(@NotNull Level worldIn, @NotNull LivingEntity livingEntityIn, @NotNull ItemStack stack, int count) {
                Player player = (Player) livingEntityIn;
                if (worldIn instanceof ServerLevel srv) {
                    for (int ii = 0; ii < 1 + Mth.nextInt(RandomSource.create(), 0, 2); ii += 1) {
                        PacketHandler.sendToTracking(srv, player.getOnPos(), new MurasamaParticlePacket(3F, player.getX(), (player.getY() + (player.getEyeHeight() / 2)), player.getZ(), 235, 0, 25));
                    }
                }

                if (player.getTicksUsingItem() == 20) {
                    player.playNotifySound(SoundsRegistry.RECHARGE.get(), SoundSource.PLAYERS, 0.6f, 1);
                }
            }

            public void performDash(@NotNull ItemStack stack, @NotNull Level level, @NotNull Player player, Vector3d pos, RandomSource rand){
                double pitch = ((player.getRotationVector().x + 90) * Math.PI) / 180;
                double yaw = ((player.getRotationVector().y + 90) * Math.PI) / 180;
                double dashDistance = getDashDistance(player);
                Vec3 dir = (player.getViewVector(0.0f).scale(dashDistance));
                player.push(dir.x, dir.y * 0.25, dir.z);
                double ii = 1D;
                if(level instanceof ServerLevel srv){
                    for(int i = 0; i < 10; i += 1){
                        double locDistance = i * 0.5D;
                        double X = Math.sin(pitch) * Math.cos(yaw) * locDistance;
                        double Y = Math.cos(pitch) * locDistance;
                        double Z = Math.sin(pitch) * Math.sin(yaw) * locDistance;
                        level.addParticle(ParticleTypes.WAX_OFF, pos.x + X + (rand.nextDouble() - 0.5D), pos.y + Y, pos.z + Z + (rand.nextDouble() - 0.5D), 0d, 0.05d, 0d);
                        for(int count = 0; count < 1 + Mth.nextInt(RandomSource.create(), 0, 2); count += 1){
                            PacketHandler.sendToTracking(srv, player.getOnPos(), new MurasamaParticlePacket(3F, (pos.x + X), (pos.y + Y), (pos.z + Z), 255, 0, 0));
                        }

                        List<LivingEntity> detectedEntities = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos.x + X - 0.5D, pos.y + Y - 0.5D, pos.z + Z - 0.5D, pos.x + X + 0.5D, pos.y + Y + 0.5D, pos.z + Z + 0.5D));
                        for(LivingEntity entity : detectedEntities){
                            if(!entity.equals(player)){
                                entity.hurt(level.damageSources().playerAttack(player), (float)((player.getAttributeValue(Attributes.ATTACK_DAMAGE) * (double)ii) + EnchantmentHelper.getSweepingDamageRatio(player) + EnchantmentHelper.getDamageBonus(stack, entity.getMobType())) * 1.35f);
                                performEffects(entity, player);
                                ValoriaUtils.chanceEffect(entity, effects, chance, arcRandom);
                                if(!player.isCreative()){
                                    stack.hurtAndBreak(getHurtAmount(detectedEntities), player, (plr) -> plr.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                                }
                            }

                            ii = ii - (1F / (detectedEntities.size() * 2));
                        }

                        if(locDistance >= distance(dashDistance, level, player)){
                            break;
                        }
                    }
                }
            }
        };
    }
}