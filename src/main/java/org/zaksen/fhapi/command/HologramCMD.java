package org.zaksen.fhapi.command;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.joml.Vector3f;
import org.zaksen.fhapi.FHApi;
import org.zaksen.fhapi.holo.BlockHologram;
import org.zaksen.fhapi.holo.IHologram;
import org.zaksen.fhapi.holo.ItemHologram;
import org.zaksen.fhapi.holo.TextHologram;
import org.zaksen.fhapi.utils.*;

public class HologramCMD implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(commandSender instanceof Player) || args.length < 2) {
            return true;
        }
        Player player = (Player) commandSender;
        String subcommand = args[0];
        try {
            switch (subcommand) {
                case "spawn": {
                    spawn(player, args);
                    break;
                }
                case "remove": {
                    int id = Integer.parseInt(args[1]);
                    Holos.removeHolo(id);
                    ChatUtils.sendPrefixMSG(player, String.format(FHApi.getFormattedMessages().get("msg_remove-holo"), id));
                    break;
                }
                case "modify": {
                    modify(player, args);
                    break;
                }
                default: {
                    ChatUtils.sendPrefixMSG(player, FHApi.getFormattedMessages().get("msg_wrong-subcommand"));
                    break;
                }
            }
        } catch (Exception e) {
            ChatUtils.sendPrefixMSG(player, FHApi.getFormattedMessages().get("msg_wrong-args"));
        }
        return true;
    }

    private void spawn(Player player, String[] args) {
        String spawnSubcommand = args[1];
        IHologram summonedHolo = null;
        switch (spawnSubcommand) {
            case "text": {
                if (args[2].equals("last")) {
                    summonedHolo = Holos.spawnText(player.getLocation());
                } else {
                    summonedHolo = Holos.spawnTextWithId(player.getLocation(), Integer.parseInt(args[2]));
                }
                break;
            }
            case "block": {
                if (args[2].equals("last")) {
                    summonedHolo = Holos.spawnBlock(player.getLocation());
                } else {
                    summonedHolo = Holos.spawnBlockWithId(player.getLocation(), Integer.parseInt(args[2]));
                }
                break;
            }
            case "item": {
                if (args[2].equals("last")) {
                    summonedHolo = Holos.spawnItem(player.getLocation());
                } else {
                    summonedHolo = Holos.spawnItemWithId(player.getLocation(), Integer.parseInt(args[2]));
                }
                break;
            }
        }
        if (summonedHolo == null) {
            ChatUtils.sendPrefixMSG(player, FHApi.getFormattedMessages().get("msg_exists-id"));
        } else {
            ChatUtils.sendPrefixMSG(player, String.format(FHApi.getFormattedMessages().get("msg_spawn-holo"), summonedHolo.getId()));
        }
    }

    private void modify(Player player, String[] args) {
        int id = Integer.parseInt(args[1]);
        IHologram holo = Holos.getHolo(id);
        if(holo.getDisplay() instanceof TextDisplay) {
            modifyText(player, (TextHologram) holo, args);
        } else if(holo.getDisplay() instanceof BlockDisplay) {
            modifyBlock(player, (BlockHologram) holo, args);
        } else if (holo.getDisplay() instanceof ItemDisplay) {
            modifyItem(player, (ItemHologram) holo, args);
        } else {
            ChatUtils.sendPrefixMSG(player, FHApi.getFormattedMessages().get("msg_wrong-args"));
        }
    }

    private void modifyItem(Player player, ItemHologram holo, String[] args) {
        String modifySubcommand = args[2];
        switch (modifySubcommand) {
            case "scale": {
                float scaleX = Float.parseFloat(args[3]);
                if (args.length > 4) {
                    float scaleY = Float.parseFloat(args[4]);
                    float scaleZ = Float.parseFloat(args[5]);
                    HoloItem.setScale(holo, new Vector3f(scaleX, scaleY, scaleZ));
                } else {
                    HoloItem.setScale(holo, scaleX);
                }
                break;
            }
            case "position": {
                double x = Double.parseDouble(args[3]);
                double y = Double.parseDouble(args[4]);
                double z = Double.parseDouble(args[5]);
                HoloItem.setLocation(holo, new Location(player.getWorld(), x, y, z));
                break;
            }
            case "glow_color": {
                int red = Integer.parseInt(args[3]);
                int green = Integer.parseInt(args[4]);
                int blue = Integer.parseInt(args[5]);
                HoloItem.setGlowColor(holo, red, green, blue);
                break;
            }
            case "billboard": {
                HoloItem.setBillboard(holo, Display.Billboard.valueOf(args[3].toUpperCase()));
                break;
            }
            case "view_range": {
                HoloItem.setViewRange(holo, Float.parseFloat(args[3]));
                break;
            }
            case "rotation": {
                float rotationX = Float.parseFloat(args[3]);
                float rotationY = Float.parseFloat(args[4]);
                HoloItem.setRotation(holo, rotationX, rotationY);
                break;
            }
            case "translation": {
                float scaleX = Float.parseFloat(args[3]);
                if (args.length > 4) {
                    float scaleY = Float.parseFloat(args[4]);
                    float scaleZ = Float.parseFloat(args[5]);
                    HoloItem.setTranslation(holo, scaleX, scaleY, scaleZ);
                } else {
                    HoloItem.setTranslation(holo, scaleX);
                }
                break;
            }
            case "left_rotation": {
                float rotationX = Float.parseFloat(args[3]);
                if (args.length > 4) {
                    float rotationY = Float.parseFloat(args[4]);
                    float rotationZ = Float.parseFloat(args[5]);
                    if (args.length > 6) {
                        HoloItem.setLRotation(holo, rotationX, rotationY, rotationZ, Float.parseFloat(args[6]));
                    } else {
                        HoloItem.setLRotation(holo, rotationX, rotationY, rotationZ);
                    }
                } else {
                    HoloItem.setLRotation(holo, rotationX);
                }
                break;
            }
            case "right_rotation": {
                float rotationX = Float.parseFloat(args[3]);
                if (args.length > 4) {
                    float rotationY = Float.parseFloat(args[4]);
                    float rotationZ = Float.parseFloat(args[5]);
                    if (args.length > 6) {
                        HoloItem.setRRotation(holo, rotationX, rotationY, rotationZ, Float.parseFloat(args[6]));
                    } else {
                        HoloItem.setRRotation(holo, rotationX, rotationY, rotationZ);
                    }
                } else {
                    HoloItem.setRRotation(holo, rotationX);
                }
                break;
            }
            case "item": {
                HoloItem.setItem(holo, new ItemStack(Material.valueOf(args[3].replaceFirst("minecraft:", "").toUpperCase())));
                break;
            }
        }
    }

    private void modifyBlock(Player player, BlockHologram holo, String[] args) {
        String modifySubcommand = args[2];
        switch (modifySubcommand) {
            case "scale": {
                float scaleX = Float.parseFloat(args[3]);
                if (args.length > 4) {
                    float scaleY = Float.parseFloat(args[4]);
                    float scaleZ = Float.parseFloat(args[5]);
                    HoloBlock.setScale(holo, new Vector3f(scaleX, scaleY, scaleZ));
                } else {
                    HoloBlock.setScale(holo, scaleX);
                }
                break;
            }
            case "position": {
                double x = Double.parseDouble(args[3]);
                double y = Double.parseDouble(args[4]);
                double z = Double.parseDouble(args[5]);
                HoloBlock.setLocation(holo, new Location(player.getWorld(), x, y, z));
                break;
            }
            case "glow_color": {
                int red = Integer.parseInt(args[3]);
                int green = Integer.parseInt(args[4]);
                int blue = Integer.parseInt(args[5]);
                HoloBlock.setGlowColor(holo, red, green, blue);
                break;
            }
            case "billboard": {
                HoloBlock.setBillboard(holo, Display.Billboard.valueOf(args[3].toUpperCase()));
                break;
            }
            case "view_range": {
                HoloBlock.setViewRange(holo, Float.parseFloat(args[3]));
                break;
            }
            case "rotation": {
                float rotationX = Float.parseFloat(args[3]);
                float rotationY = Float.parseFloat(args[4]);
                HoloBlock.setRotation(holo, rotationX, rotationY);
                break;
            }
            case "translation": {
                float scaleX = Float.parseFloat(args[3]);
                if (args.length > 4) {
                    float scaleY = Float.parseFloat(args[4]);
                    float scaleZ = Float.parseFloat(args[5]);
                    HoloBlock.setTranslation(holo, scaleX, scaleY, scaleZ);
                } else {
                    HoloBlock.setTranslation(holo, scaleX);
                }
                break;
            }
            case "left_rotation": {
                float rotationX = Float.parseFloat(args[3]);
                if (args.length > 4) {
                    float rotationY = Float.parseFloat(args[4]);
                    float rotationZ = Float.parseFloat(args[5]);
                    if (args.length > 6) {
                        HoloBlock.setLRotation(holo, rotationX, rotationY, rotationZ, Float.parseFloat(args[6]));
                    } else {
                        HoloBlock.setLRotation(holo, rotationX, rotationY, rotationZ);
                    }
                } else {
                    HoloBlock.setLRotation(holo, rotationX);
                }
                break;
            }
            case "right_rotation": {
                float rotationX = Float.parseFloat(args[3]);
                if (args.length > 4) {
                    float rotationY = Float.parseFloat(args[4]);
                    float rotationZ = Float.parseFloat(args[5]);
                    if (args.length > 6) {
                        HoloBlock.setRRotation(holo, rotationX, rotationY, rotationZ, Float.parseFloat(args[6]));
                    } else {
                        HoloBlock.setRRotation(holo, rotationX, rotationY, rotationZ);
                    }
                } else {
                    HoloBlock.setRRotation(holo, rotationX);
                }
                break;
            }
            case "block": {
                HoloBlock.setBlock(holo, Material.valueOf(args[3].replaceFirst("minecraft:", "").toUpperCase()).createBlockData());
                break;
            }
        }
    }

    private void modifyText(Player player, TextHologram holo, String[] args) {
        String modifySubcommand = args[2];
        switch (modifySubcommand) {
            case "scale": {
                float scaleX = Float.parseFloat(args[3]);
                if (args.length > 4) {
                    float scaleY = Float.parseFloat(args[4]);
                    float scaleZ = Float.parseFloat(args[5]);
                    HoloText.setScale(holo, new Vector3f(scaleX, scaleY, scaleZ));
                } else {
                    HoloText.setScale(holo, scaleX);
                }
                break;
            }
            case "position": {
                double x = Double.parseDouble(args[3]);
                double y = Double.parseDouble(args[4]);
                double z = Double.parseDouble(args[5]);
                HoloText.setLocation(holo, new Location(player.getWorld(), x, y, z));
                break;
            }
            case "glow_color": {
                int red = Integer.parseInt(args[3]);
                int green = Integer.parseInt(args[4]);
                int blue = Integer.parseInt(args[5]);
                HoloText.setGlowColor(holo, red, green, blue);
                break;
            }
            case "billboard": {
                HoloText.setBillboard(holo, Display.Billboard.valueOf(args[3].toUpperCase()));
                break;
            }
            case "view_range": {
                HoloText.setViewRange(holo, Float.parseFloat(args[3]));
                break;
            }
            case "rotation": {
                float rotationX = Float.parseFloat(args[3]);
                float rotationY = Float.parseFloat(args[4]);
                HoloText.setRotation(holo, rotationX, rotationY);
                break;
            }
            case "translation": {
                float scaleX = Float.parseFloat(args[3]);
                if (args.length > 4) {
                    float scaleY = Float.parseFloat(args[4]);
                    float scaleZ = Float.parseFloat(args[5]);
                    HoloText.setTranslation(holo, scaleX, scaleY, scaleZ);
                } else {
                    HoloText.setTranslation(holo, scaleX);
                }
                break;
            }
            case "left_rotation": {
                float rotationX = Float.parseFloat(args[3]);
                if (args.length > 4) {
                    float rotationY = Float.parseFloat(args[4]);
                    float rotationZ = Float.parseFloat(args[5]);
                    if (args.length > 6) {
                        HoloText.setLRotation(holo, rotationX, rotationY, rotationZ, Float.parseFloat(args[6]));
                    } else {
                        HoloText.setLRotation(holo, rotationX, rotationY, rotationZ);
                    }
                } else {
                    HoloText.setLRotation(holo, rotationX);
                }
                break;
            }
            case "right_rotation": {
                float rotationX = Float.parseFloat(args[3]);
                if (args.length > 4) {
                    float rotationY = Float.parseFloat(args[4]);
                    float rotationZ = Float.parseFloat(args[5]);
                    if (args.length > 6) {
                        HoloText.setRRotation(holo, rotationX, rotationY, rotationZ, Float.parseFloat(args[6]));
                    } else {
                        HoloText.setRRotation(holo, rotationX, rotationY, rotationZ);
                    }
                } else {
                    HoloText.setRRotation(holo, rotationX);
                }
                break;
            }
            case "alignment": {
                HoloText.setAlignment(holo, TextDisplay.TextAlignment.valueOf(args[3].toUpperCase()));
                break;
            }
            case "line_width": {
                HoloText.setLineWidth(holo, Integer.parseInt(args[3]));
                break;
            }
            case "see_through": {
                HoloText.setSeeThrough(holo, Boolean.parseBoolean(args[3]));
                break;
            }
            case "text_opacity": {
                String opacity = args[3];
                HoloText.setTextOpacity(holo, Byte.parseByte(opacity));
                break;
            }
            case "shadow": {
                HoloText.setShadow(holo, Boolean.parseBoolean(args[3]));
                break;
            }
            case "background": {
                int red = Integer.parseInt(args[3]);
                int green = Integer.parseInt(args[4]);
                int blue = Integer.parseInt(args[5]);
                if (args.length > 6) {
                    HoloText.setBackgroundColor(holo, red, green, blue, Integer.parseInt(args[6]));
                } else {
                    HoloText.setBackgroundColor(holo, red, green, blue);
                }
                break;
            }
            case "text": {
                StringBuilder newText = new StringBuilder(args[3]);
                for (int i = 4; i < args.length; i++) {
                    newText.append(" ").append(args[i]);
                }
                HoloText.setFormattedText(holo, newText.toString());
                break;
            }
        }
    }
}