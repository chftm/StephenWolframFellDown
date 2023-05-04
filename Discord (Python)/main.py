import discord
from discord import app_commands
from discord.ext import commands
from discord import ui

# ⬇ paste your bot token below
TOKEN = ""
# ⬆ paste your bot token above

intents = discord.Intents.default()
client = discord.Client(intents=intents)
tree = app_commands.CommandTree(client)

swfd_fallen = False

@tree.command(name="swfd", description="Make Stephen Wolfram fall!")
async def swfd(interaction):
    global swfd_fallen
    embed=discord.Embed(color=0xff9500)
    embed.add_field(name="", value="", inline=False)
    embed.set_image(url="https://i.imgur.com/pEPkZho.jpg")
    button = ui.Button(label="Make Stephen fall!")
    view = ui.View()
    view.add_item(button)
    await interaction.response.send_message(embed=embed, view=view)
    button.callback = on_interaction

@commands.Cog.listener()
async def on_interaction(interaction):
    view = ui.View()
    global swfd_fallen
    if swfd_fallen:
        button_edit = ui.Button(label="Make Stephen fall!")
        em=discord.Embed(color=0xff9500)
        em.add_field(name="", value="", inline=False)
        em.set_image(url="https://i.imgur.com/pEPkZho.jpg")
    else:
        button_edit = ui.Button(label="Make Stephen stand!")
        em=discord.Embed(color=0xff9500)
        em.add_field(name="Stephen Wolfram fell down!", value="", inline=False)
        em.set_image(url="https://i.imgur.com/gRqoRAW.jpg")
    swfd_fallen = not swfd_fallen
    view.add_item(button_edit)
    await interaction.response.edit_message(embed=em, view=view)
    button_edit.callback = on_interaction


client.run(TOKEN)
