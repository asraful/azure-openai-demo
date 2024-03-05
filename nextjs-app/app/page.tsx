"use client";

import React, { useState } from "react";
import Dashboard from "@/app/playground/page";

const IndexPage = () => {
  // const [isFetching, setIsFetching] = useState(false);
  //
  // const handleClick = async () => {
  //   setIsFetching(true);
  //   // Perform any other actions before fetching if needed
  //   setIsFetching(false); // Assuming fetch is performed inside MyComponent
  // };

  return (
    <div>
      <Dashboard />
      {/*<h1>My Page</h1>*/}
      {/*<button onClick={handleClick} disabled={isFetching}>*/}
      {/*  {isFetching ? 'Fetching Data...' : 'Fetch Data'}*/}
      {/*</button>*/}
      {/*<DataComponent isFetching={isFetching} />*/}
    </div>
  );
};

export default IndexPage;
