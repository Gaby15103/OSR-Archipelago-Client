# Minecraft Ozone Skyblock Reborn Randomizer Setup Guide

## Required Software

- Minecraft Java Edition from
  the [Minecraft Java Edition Store Page](https://www.minecraft.net/en-us/store/minecraft-java-edition)
- Ozone skyblock reborn 1.14.1 server, use the server file from [The Latest Release](https://github.com/Gaby15103/OSR-Archipelago-Client/releases/latest)
- Ozone skyblock reborn client [modpack](https://www.curseforge.com/minecraft/modpacks/ozone-skyblock-reborn/files/6091411) from your modpack manager like [prisme](https://prismlauncher.org) and [curseforge](https://www.curseforge.com/download/app)
- [java 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html) installed to run the run.bat file or run.sh if on linux, for starting the minecraft server.
- Archipelago from the [Archipelago Releases Page](https://github.com/ArchipelagoMW/Archipelago/releases)
   - (select `Minecraft Client` during installation.)
- RAM 
  - At least 16 GB of RAM is recommended 
  - For hosting both the modded Minecraft server and client on the same machine, 32 GB is highly recommended 
  - Alternatively, you can host the modded server on a separate machine.

## 🧾 Configuring your YAML file

### What is a YAML file and why do I need one?

See the guide on setting up a basic YAML at the Archipelago setup
guide: [Basic Multiworld Setup Guide](/tutorial/archipelago/setup/en)

## Installation

### 1. Extract the server archive
Unzip OSR.aprandomizer.Server.-.1.14.1.zip into a directory of your choice.

### 2. Add your data file
Inside the extracted server folder, you’ll find an AP-DATA directory. Place your .apmc file (received from the game host) into this folder.

### 3. Start the server
Double-click run.bat (or run run.sh on Linux) from the root of the server folder.

## 🌍Joining a MultiWorld Game

### Obtain Your Minecraft Data File

**Only one yaml file needs to be submitted per Ozone Skyblock Reborn minecraft world regardless of how many players play on it.**

When you join a multiworld game, you will be asked to provide your YAML and an apworld file to whoever is hosting. Once that is done,
the host will provide you with either a link to download your data file, or with a zip file containing everyone's data
files. Your data file should have a `.apmc` extension.

### Connect to the MultiServer

Using Ozone Skyblock Reborn 1.14.1 connect to the server `localhost`.

Once you are in game type `/connect <AP-Address> (Port) (Password)` where `<AP-Address>` is the address of the
Archipelago server. `(Port)` is only required if the Archipelago server is not using the default port of 38281. `(Password)` is only required if the Archipelago server you are using has a password set.

### ▶️ Play the game

When the console tells you that you have joined the room, you're all set. Congratulations on successfully joining a
multiworld game! At this point any additional minecraft players may connect to your forge server. To start the game once
everyone is ready use the command `/start`.

