"use client";

import { Card, Metric, Text, Title, BarList, Flex, Grid } from "@tremor/react";
import Askaboutdata from "@/app/playground/askaboutdata";

export default function PlaygroundPage() {
  return (
    <main className="p-4 md:p-10 mx-auto max-w-7xl">
      <Askaboutdata />
    </main>
  );
}
