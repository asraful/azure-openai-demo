"use client";
import { fetchData } from "next-auth/client/_utils";
import React, { useState } from "react";
import { Card, Grid, Text, Title } from "@tremor/react";

export default function AskWindow() {
  const [response, setResponse] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(false);

  const [question, setQuestion] = useState({
    userQuestion: "",
  });

  const handleChange = (event: React.ChangeEvent<any>) => {
    setQuestion({
      ...question,
      userQuestion: event.target.value,
    });
  };

  const fetchData = async (e: React.ChangeEvent<any>) => {
    e.preventDefault();
    try {
      setIsLoading(true);

      const response = await fetch("/api/cognitive/search", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(question),
      });
      const data = await response.json();
      console.log(data);
      setResponse(data.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <>
      <form>
        <div className="w-full mb-4 border border-gray-200 rounded-lg bg-gray-50 dark:bg-gray-700 dark:border-gray-600">
          <div className="px-4 py-2 bg-white rounded-t-lg dark:bg-gray-800">
            <label htmlFor="comment" className="sr-only">
              Your query
            </label>
            <textarea
              id="comment"
              rows={4}
              className="w-full px-0 text-sm text-gray-900 bg-white border-0 dark:bg-gray-800 focus:ring-0 dark:text-white dark:placeholder-gray-400"
              placeholder="Ask about your data..."
              required
              onChange={handleChange}
            ></textarea>
          </div>
          <div className="flex items-center justify-between px-3 py-2 border-t dark:border-gray-600">
            <button
              type="submit"
              onClick={fetchData}
              disabled={isLoading}
              className="inline-flex items-center py-2.5 px-4 text-xs font-medium text-center text-white bg-blue-700 rounded-lg focus:ring-4 focus:ring-blue-200 dark:focus:ring-blue-900 hover:bg-blue-800"
            >
              {isLoading ? "Loading..." : "Data Insights"}
            </button>
          </div>
        </div>
      </form>
      <p className="ms-auto text-xs text-gray-500 dark:text-gray-400">
        AI powered data insights,
        <a
          href="#"
          className="text-blue-600 dark:text-blue-500 hover:underline"
        >
          @asraful{" "}
        </a>
        .
      </p>

      <Grid>
        <Card className="mt-8 border-2">
          <Title>AI (RAG) Response</Title>
          <Text>Lets understand our data better</Text>
          {response && (
            <div>
              <pre className="text-wrap">{response}</pre>
            </div>
          )}
        </Card>
      </Grid>
    </>
  );
}
