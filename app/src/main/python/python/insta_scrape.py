try:
    # import argparse
    import json
    import requests

except ModuleNotFoundError:
    print("Please download dependencies from requirement.txt")
except Exception as ex:
    print(ex)

def clean_non_utf8_chars(input_data):
    if isinstance(input_data, str):
        # If the input is a string, clean it and return the cleaned string
        cleaned_string = ""
        for char in input_data:
            if ord(char) < 128:
                cleaned_string += char
            else:
                cleaned_string += ' '
        return cleaned_string
    elif isinstance(input_data, dict):
        # If the input is a dictionary, recursively clean its values
        cleaned_dict = {}
        for key, value in input_data.items():
            cleaned_value = clean_non_utf8_chars(value)
            cleaned_dict[key] = cleaned_value
        return cleaned_dict
    elif isinstance(input_data, list):
        # If the input is a list, recursively clean its elements
        cleaned_list = []
        for item in input_data:
            cleaned_item = clean_non_utf8_chars(item)
            cleaned_list.append(cleaned_item)
        return cleaned_list
    else:
        # For other data types, return as is
        return input_data


'''can scrap only public instagram accounts'''


class Instagram:
    @staticmethod
    def build_param(username):
        params = {
            'username': username,
        }
        return params

    @staticmethod
    def build_headers(username):
        return {
            'authority': 'www.instagram.com',
            'accept': '*/*',
            'accept-language': 'en-US,en;q=0.9',
            'referer': f'https://www.instagram.com/{username}/',
            'sec-ch-prefers-color-scheme': 'dark',
            'sec-ch-ua': '"Not?A_Brand";v="8", "Chromium";v="108", "Microsoft Edge";v="108"',
            'sec-fetch-dest': 'empty',
            'sec-fetch-mode': 'cors',
            'sec-fetch-site': 'same-origin',
            'x-asbd-id': '198387',
            'x-csrftoken': 'VUm8uVUz0h2Y2CO1SwGgVAG3jQixNBmg',
            'x-ig-app-id': '936619743392459',
            'x-ig-www-claim': '0',
            'x-requested-with': 'XMLHttpRequest',
        }

    @staticmethod
    def make_request(url, params, headers, proxy=None):
        response = None
        if proxy:
            proxy_dict = {
                'http': f'http://{proxy}',
                'https': f'http://{proxy}'
            }
            response = requests.get(
                url, headers=headers, params=params, proxies=proxy_dict)
        else:
            response = requests.get(
                url, headers=headers, params=params)
        return response

    @staticmethod
    def scrap_profile(username, proxy = None):
        try:
            headers = Instagram.build_headers(username)
            params = Instagram.build_param(username)
            response = Instagram.make_request('https://www.instagram.com/api/v1/users/web_profile_info/',
                                              headers=headers, params=params, proxy=proxy)
            if response.status_code == 200:
                profile_data = response.json()['data']['user']
                return json.dumps(profile_data)
            else:
                print('Error : ', response.status_code, response.text)
        except Exception as ex:
            print(ex)

def main(username):
    d = Instagram.scrap_profile(username)

    if d is None:
        return
    data = json.loads(d)
    print(d)
    # print(type(Instagram.scrap_tagged(args.username, args.proxy)))
    # data1 = json.loads(Instagram.scrap_tagged(args.username, args.proxy))
    # print(data1)
    # # print(type(data))
    cleaned_json_data = clean_non_utf8_chars(data)

    a = cleaned_json_data["edge_owner_to_timeline_media"]["edges"]
    str_data = ""
    for i in range(len(a)):
        b = a[i]
        c = b["node"]["edge_media_to_caption"]["edges"]
        for j in range(len(c)):
            d = c[j]
            e = d["node"]["text"]
            # print(e)
            str_data += e

    return str_data

    # file_path = "tech_data.txt"
    # with open(file_path, "w") as file:
    #     # file.write("\nSTART\n")
    #     file.write(args.username)
    #     file.write("\n")
    #     file.write(str_data)
