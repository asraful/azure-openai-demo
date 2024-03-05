"use client";

import { Card, Title, Text } from "@tremor/react";
import AskWindow from "@/app/playground/getairesponse";

export default function Askaboutdata() {
  return (
    <Card className="mt-8 border-2">
      <Title>Data Analysis</Title>
      <Text>Lets understand our data better</Text>
      <h4>Example Query:</h4>
      <Text>
        <ul>
          <li>Tell me about the data source.</li>
          <li>Birth Rate.</li>
          <li>Predict population of Finland by 2060.</li>
        </ul>
      </Text>
      <br></br>

      <AskWindow />
    </Card>
  );
}
