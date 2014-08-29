package ru.vimvd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TableParser {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("data.txt"));
		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.trim().isEmpty()) {
				continue;
			}
			// System.out.println(line);
			String[] tokens = line.split("[\t]");
			String template = "<ru.vimvd.RowDTO><header>$1</header><Mmin>$2</Mmin><Mmax>$3</Mmax><Tmin>$4</Tmin><Tmax>$5</Tmax></ru.vimvd.RowDTO>";
			if(tokens.length==5){
				String result=template.replace("$1", tokens[0]).replace("$2", tokens[1]).replace("$3", tokens[2]).replace("$4", tokens[3]).replace("$5", tokens[4]);
				System.out.println(result);
			}
			

		}

	}
}
