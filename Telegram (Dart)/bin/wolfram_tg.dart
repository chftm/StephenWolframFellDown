import 'package:teledart/model.dart';
import 'package:teledart/teledart.dart';
import 'package:teledart/telegram.dart';
import 'package:wolfram_tg/token.dart';

Future<void> main() async {
  final username = (await Telegram(token).getMe()).username;
  final bot = TeleDart(token, Event(username!));

  bot.onCommand('start').listen((message) {
    final keyboard = ReplyKeyboardMarkup(
      keyboard: [
        [
          KeyboardButton(text: 'ПРИЗВАТЬ ВОЛЬФРАМА!!!!!!'),
        ],
      ],
    );

    message.reply(
      'Добро пожаловать в Стивен Вольфрам Упал !!!!!!!!!!!!!!!!!!!!',
      replyMarkup: keyboard,
    );
  });

  bot.onMessage(keyword: 'ПРИЗВАТЬ ВОЛЬФРАМА!!!!!!').listen((message) {
    final keyboard = InlineKeyboardMarkup(
      inlineKeyboard: [
        [
          InlineKeyboardButton(
            text: 'УРОНИТЬ ВОЛЬФРАМА!!!!!',
            callbackData: 'fall',
          ),
        ],
      ],
    );

    message.replyPhoto(
      'AgACAgIAAxkBAAMSY_8UyaWfMKJouPmIQIxvicbVTfYAAg7BMRskRvlLau8vwX6q-yoBAAMCAANzAAMuBA',
      replyMarkup: keyboard,
    );
  });

  bot.onCallbackQuery().listen((query) {
    final chatId = query.message!.chat.id;
    if (query.data == 'fall') {
      final keyboard = InlineKeyboardMarkup(
        inlineKeyboard: [
          [
            InlineKeyboardButton(
              text: 'ПОМОЧЬ СТИВЕНУ ПОДНЯТЬСЯ!!!!!!!!!!!',
              callbackData: 'stand',
            ),
          ],
        ],
      );

      bot
        ..editMessageMedia(
          chatId: chatId,
          messageId: query.message!.messageId,
          media: InputMediaPhoto(
            media:
                'AgACAgIAAxkBAAMUY_8U0z_h42HIs7QgO_sJ_ZYKiPwAAg_BMRskRvlL8gfhNGMBV5IBAAMCAANzAAMuBA',
          ),
          replyMarkup: keyboard,
        )
        ..sendMessage(chatId, 'СТИВЕН ВОЛЬФРАМ УПАЛ!!!!!!!!!!!!!!!!');
    } else if (query.data == 'stand') {
      final keyboard = InlineKeyboardMarkup(
        inlineKeyboard: [
          [
            InlineKeyboardButton(
              text: 'УРОНИТЬ ВОЛЬФРАМА!!!!!',
              callbackData: 'fall',
            ),
          ],
        ],
      );

      bot
        ..editMessageMedia(
          chatId: chatId,
          messageId: query.message!.messageId,
          media: InputMediaPhoto(
            media:
                'AgACAgIAAxkBAAMSY_8UyaWfMKJouPmIQIxvicbVTfYAAg7BMRskRvlLau8vwX6q-yoBAAMCAANzAAMuBA',
          ),
          replyMarkup: keyboard,
        )
        ..sendMessage(chatId, 'СТИВЕН ВОЛЬФРАМ ВСТАЛ!!!!!!!!!!!!!!!!');
    }
  });

  bot.onMessage().listen((message) {
    if (message.photo != null) {
      message.reply(message.photo!.first.fileId);
    }
  });

  bot.start();
}
