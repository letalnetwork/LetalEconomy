# LetalEconomyPlugin

<!-- TOC -->
* [LetalEconomyPlugin](#letaleconomyplugin)
* [COMMANDS](#commands)
* [PLACEHOLDERS](#placeholders)
<!-- TOC -->

# COMMANDS

`/letaleconomy` - `aliases: /leconomy,/economy,/coins,/letalcoins`

| `command`                                          | `permission`          | `description`         |
|----------------------------------------------------|-----------------------|-----------------------|
| `/letaleconomy user [balance,give,take,set,reset]` | `letaleconomy.admin`  | `user manage command` |
| `/letaleconomy baltop <economy>`                   | `letaleconomy.baltop` | `baltop view command` |
| `/letaleconomy pay <player> <economy> <amount>`    | `letaleconomy.pay`    | `pay command`         |
| `/letaleconomy balance [player]`                   | `none`                | `see balance `        |
| `/letaleconomy reload `                            | `letaleconomy.admin`  | `reloads the plugin ` | 

# PLACEHOLDERS


| `placeholder`                                  | `description`                                   |
|------------------------------------------------|-------------------------------------------------|
| `%letaleconomy_balance_<economy>%`             | `shows balance of a player (raw)`               |
| `%letaleconomy_balance_formatted_<economy>%`   | `shows balance of a player (formatted: 1k, 4M)` |
| `%letaleconomy_baltop_<economy>_<value/name>%` | `baltop related placeholder`                    |
| `%letaleconomy_baltop_position_<economy>%`     | `shows the position of the player in a baltop`  |


