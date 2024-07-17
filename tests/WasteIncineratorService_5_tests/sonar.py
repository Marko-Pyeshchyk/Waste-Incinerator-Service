import time

def main():
    num = 310
    time.sleep(1)
    num += 1
    print(num, flush=True)

    try:
        while True:
            print(num, flush=True)
            num += -1
            time.sleep(3)
            if num == 295:
                num = 305
    except KeyboardInterrupt:
        print("\nError")

if __name__ == "__main__":
    main()