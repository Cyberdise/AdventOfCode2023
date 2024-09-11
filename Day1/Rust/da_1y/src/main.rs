use std::fs;

fn main() {
    println!("Hello, world!");

    let input = "./input.txt";

    let contents = fs::read_to_string("./src/input.txt").expect("msg");
    println!("{contents}");

    let all_numbers = contents.lines().split(char::is_alphabetic()) {

    };
}