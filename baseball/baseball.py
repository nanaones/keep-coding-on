from iconservice import *


class Baseball(IconScoreBase):
    TAG = 'Baseball'

    _PLAYERS = 'players'
    _PLAYERS_NUMBERS = 'players_numbers'
    _USERNAME = 'username'

    def __init__(self, db: IconScoreDatabase) -> None:
        super().__init__(db)
        self._players = ArrayDB(self._PLAYERS, db, value_type=Address)
        self._players_numbers = DictDB(self._PLAYERS_NUMBERS, db, value_type=int)
        self._username = DictDB(self._USERNAME, db, value_type=str)

    def on_install(self):
        super().on_install()

    def on_update(self):
        super().on_update()

    # Ground
    def join(self, player=Address):
        pass

    def check_players_number(self, player=Address):
        players_number = len(self._players)
        if players_number == 2:
            pass
        else:
            self._players.put(player)

    def check_game_status(self):
        pass

# Players
    def set_username(self, username=str):
        pass

    def is_player_ready(self):
        pass

    def select_one(self):
        pass

    def set_each_numbers(self):
        pass

    def guess_numbers(self):
        pass

# Game Helper
    def rock_scissors_paper(self):
        pass

    def get_players_numbers(self):
        pass

    def get_result(self):
        pass

    def check_guessing_numbers(self):
        pass
