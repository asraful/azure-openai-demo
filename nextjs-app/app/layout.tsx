import "./globals.css";

import Nav from "./nav";
import { Suspense } from "react";

export const metadata = {
  title: "Azure OpenAI Demo",
  description: "Custom data analysis with Azure OpenAI",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en" className="h-full bg-gray-50">
      <body className="h-full">
        <Suspense>
          <Nav />
        </Suspense>
        {children}
      </body>
    </html>
  );
}
