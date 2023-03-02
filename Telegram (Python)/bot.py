import telebot

with open('token.txt') as token_file:
    token = token_file.readline()

bot = telebot.TeleBot(token)
mes = 0
chat_id = 0


@bot.message_handler(commands=['start'])
def start(message):
    markup = telebot.types.ReplyKeyboardMarkup(resize_keyboard=True)
    button = telebot.types.KeyboardButton('ПРИЗВАТЬ САМОГО СТИВЕНА!')
    markup.add(button)
    bot.send_message(
        message.chat.id, 'ПРИВЕЕЕТ!!!! ВАС ПРИВЕТСТВУЕТ БОТ СТИВЕН ВОЛЬФРАМ УПАЛ!!!! ВРЕМЯ ЕГО ПРИЗВАТЬ!!!!!!', reply_markup=markup)


@bot.message_handler(content_types='text')
def send_normal_wolfram(message):
    global mes
    global chat_id
    chat_id = message.chat.id
    inline_markup = telebot.types.InlineKeyboardMarkup()
    inline_bt = telebot.types.InlineKeyboardButton(
        'УПАСТЬ!!!!!!!', callback_data='down')
    inline_markup.add(inline_bt)
    if message.text == 'ПРИЗВАТЬ САМОГО СТИВЕНА!':
        mes = bot.send_photo(message.chat.id, photo=open(
            'normal_wolfram.jpg', 'rb'), reply_markup=inline_markup)


@bot.callback_query_handler(func=lambda call: True)
def call_back1(call):
    global mes
    global chat_id
    inline_markup1 = telebot.types.InlineKeyboardMarkup()
    inline_bt1 = telebot.types.InlineKeyboardButton(
        'ВСТАТЬ!!!!!!!', callback_data='up')
    inline_markup1.add(inline_bt1)
    inline_markup2 = telebot.types.InlineKeyboardMarkup()
    inline_bt2 = telebot.types.InlineKeyboardButton(
        'УПАСТЬ!!!!!!!', callback_data='down')
    inline_markup2.add(inline_bt2)
    mes_id = mes.message_id
    if call.data == 'down':
        bot.edit_message_media(media=telebot.types.InputMedia('photo', telebot.types.InputFile(open('stupid_wolfram.jpg', 'rb'))), message_id=mes_id,
                               chat_id=chat_id, reply_markup=inline_markup1)
        bot.send_message(chat_id, 'СТИВЕН ВОЛЬФРАМ УПАЛ!!!!')
    if call.data == 'up':
        bot.edit_message_media(media=telebot.types.InputMedia('photo', telebot.types.InputFile(open('normal_wolfram.jpg', 'rb'))), message_id=mes_id,
                               chat_id=chat_id, reply_markup=inline_markup2)
        bot.send_message(chat_id, 'СТИВЕН ВОЛЬФРАМ ВСТАЛ!!!!')


bot.infinity_polling()
