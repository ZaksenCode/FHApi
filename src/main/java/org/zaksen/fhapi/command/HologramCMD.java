package org.zaksen.fhapi.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.joml.Vector3f;
import org.zaksen.fhapi.text.Hologram;
import org.zaksen.fhapi.text.HologramManager;
import org.zaksen.fhapi.utils.HoloUtil;

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
                    String newText = args[1];
                    Hologram summonedHolo;
                    if (args[2].equals("last")) {
                        summonedHolo = HoloUtil.summonHolo(player.getLocation(), newText);
                    } else {
                        summonedHolo = HoloUtil.summonHoloWithId(player.getLocation(), newText, Integer.parseInt(args[2]));
                    }
                    if (summonedHolo == null) {
                        player.sendMessage(HoloUtil.formatText("&cThat id was registered!"));
                    } else {
                        player.sendMessage(HoloUtil.formatText("&aCreate new hologram with id: " + summonedHolo.getId()));
                    }
                    break;
                }
                case "remove": {
                    int id = Integer.parseInt(args[1]);
                    HoloUtil.removeHolo(id);
                    player.sendMessage(HoloUtil.formatText("&aRemove hologram with id: " + id));
                    break;
                }
                case "modify": {
                    try {
                        String modifySubcommand = args[2];
                        int id = Integer.parseInt(args[1]);
                        Hologram holo = HologramManager.getById(id);
                        switch (modifySubcommand) {
                            case "scale": {
                                float scaleX = Float.parseFloat(args[3]);
                                if (args.length > 4) {
                                    float scaleY = Float.parseFloat(args[4]);
                                    float scaleZ = Float.parseFloat(args[5]);
                                    HoloUtil.setScale(holo, new Vector3f(scaleX, scaleY, scaleZ));
                                } else {
                                    HoloUtil.setScale(holo, scaleX);
                                }
                                break;
                            }
                            case "alignment": {
                                HoloUtil.setAlignment(holo, TextDisplay.TextAlignment.valueOf(args[3].toUpperCase()));
                                break;
                            }
                            case "billboard": {
                                HoloUtil.setBillboard(holo, Display.Billboard.valueOf(args[3].toUpperCase()));
                                break;
                            }
                            case "line_width": {
                                HoloUtil.setLineWidth(holo, Integer.parseInt(args[3]));
                                break;
                            }
                            case "position": {
                                double x = Double.parseDouble(args[3]);
                                double y = Double.parseDouble(args[4]);
                                double z = Double.parseDouble(args[5]);
                                HoloUtil.setLocation(holo, new Location(player.getWorld(), x, y, z));
                                break;
                            }
                            case "glow_color": {
                                int red = Integer.parseInt(args[3]);
                                int green = Integer.parseInt(args[4]);
                                int blue = Integer.parseInt(args[5]);
                                HoloUtil.setGlowColor(holo, red, green, blue);
                                break;
                            }
                            case "see_through": {
                                HoloUtil.setSeeThrough(holo, Boolean.parseBoolean(args[3]));
                                break;
                            }
                            case "text_opacity": {
                                String opacity = args[3];
                                HoloUtil.setTextOpacity(holo, Byte.parseByte(opacity));
                                break;
                            }
                            case "shadow": {
                                HoloUtil.setShadow(holo, Boolean.parseBoolean(args[3]));
                                break;
                            }
                            case "background": {
                                int red = Integer.parseInt(args[3]);
                                int green = Integer.parseInt(args[4]);
                                int blue = Integer.parseInt(args[5]);
                                if (args.length > 6) {
                                    HoloUtil.setBackgroundColor(holo, red, green, blue, Integer.parseInt(args[6]));
                                } else {
                                    HoloUtil.setBackgroundColor(holo, red, green, blue);
                                }
                                break;
                            }
                            case "text": {
                                StringBuilder newText = new StringBuilder(args[3]);
                                for (int i = 4; i < args.length; i++) {
                                    newText.append(" ").append(args[i]);
                                }
                                HoloUtil.setFormattedText(holo, newText.toString());
                                break;
                            }
                            case "view_range": {
                                HoloUtil.setViewRange(holo, Float.parseFloat(args[3]));
                                break;
                            }
                            case "translation": {
                                float scaleX = Float.parseFloat(args[3]);
                                if (args.length > 4) {
                                    float scaleY = Float.parseFloat(args[4]);
                                    float scaleZ = Float.parseFloat(args[5]);
                                    HoloUtil.setTranslation(holo, scaleX, scaleY, scaleZ);
                                } else {
                                    HoloUtil.setTranslation(holo, scaleX);
                                }
                                break;
                            }
                            case "left_rotation": {
                                float rotationX = Float.parseFloat(args[3]);
                                if (args.length > 4) {
                                    float rotationY = Float.parseFloat(args[4]);
                                    float rotationZ = Float.parseFloat(args[5]);
                                    if (args.length > 6) {
                                        HoloUtil.setLRotation(holo, rotationX, rotationY, rotationZ, Float.parseFloat(args[6]));
                                    } else {
                                        HoloUtil.setLRotation(holo, rotationX, rotationY, rotationZ);
                                    }
                                } else {
                                    HoloUtil.setLRotation(holo, rotationX);
                                }
                                break;
                            }
                            case "right_rotation": {
                                float rotationX = Float.parseFloat(args[3]);
                                if (args.length > 4) {
                                    float rotationY = Float.parseFloat(args[4]);
                                    float rotationZ = Float.parseFloat(args[5]);
                                    if (args.length > 6) {
                                        HoloUtil.setRRotation(holo, rotationX, rotationY, rotationZ, Float.parseFloat(args[6]));
                                    } else {
                                        HoloUtil.setRRotation(holo, rotationX, rotationY, rotationZ);
                                    }
                                } else {
                                    HoloUtil.setRRotation(holo, rotationX);
                                }
                                break;
                            }
                            case "rotation": {
                                float rotationX = Float.parseFloat(args[3]);
                                float rotationY = Float.parseFloat(args[4]);
                                HoloUtil.setRotation(holo, rotationX, rotationY);
                                break;
                            }
                        }
                        break;
                    } catch (Exception e) {
                        player.sendMessage(HoloUtil.formatText("&cWrong command args!"));
                        break;
                    }
                }
                default: {
                    player.sendMessage(HoloUtil.formatText("&cWrong subcommand for hologram"));
                    break;
                }
            }
        } catch (Exception e) {
            player.sendMessage(HoloUtil.formatText("&cWrong command args!"));
        }
        return true;
    }
}